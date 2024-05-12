package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Photo;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectPaymentMethodPage;
import com.mhsenpc.v2raybot.bot.pages.HomePage;
import com.mhsenpc.v2raybot.bot.pages.WaitForCouponPage;
import com.mhsenpc.v2raybot.bot.pages.WaitForReceiptPage;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.PhotoRepository;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.PlanFormatter;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.PhotoSize;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class BuyController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PlanFormatter planFormatter;

    @Override
    public void invoke(Update update) {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            callbackQueryData = update.getCallbackQuery().getData();
        }
        User user = this.userRepository.findByChatId(chatId);

        if (message.equals(HomePage.BTN_BUY_CONFIG)) {

            SendMessageMethod planMessageItem = new SendMessageMethod();
            planMessageItem.setChatId(chatId);
            planMessageItem.setToken(this.config.getToken());

            List<Plan> planList = planRepository.findAll();
            if(planList.isEmpty()){
                planMessageItem.setText("متاسفانه هیچ تعرفه ای در سیستم تعریف نشده است. لطفا از بخش تنظیمات پلن های خود را تعریف کنید");
                this.requestHandler.send(planMessageItem, Message.class);
            }
            else {
                this.sendMessage("لطفا از بین تعرفه های زیر یک گزینه را انتخاب گنید");

                // set step
                BuyAccountRequest buyAccountRequestPayload = new BuyAccountRequest();
                buyAccountRequestPayload.setChatId(chatId);
                buyAccountRequestPayload.setUserId(user.getUserId());
                UserStepWithPayload stepWithPayload = new UserStepWithPayload(UserStep.BUY_SELECT_PLAN, buyAccountRequestPayload);
                userStepService.set(chatId, stepWithPayload);

                // send plans one by one
                for (Plan plan : planList) {
                    planMessageItem.setText(planFormatter.format(plan));
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    inlineKeyboardMarkup.addRow(new InlineKeyboardButton("انتخاب", String.valueOf(plan.getPlanId())));
                    planMessageItem.setReplyMarkup(inlineKeyboardMarkup);
                    this.requestHandler.send(planMessageItem, Message.class);
                }
            }

            return;
        }

        BuyAccountRequest currentPayload = (BuyAccountRequest) currentStepWithPayload.getPayload();
        switch (currentStepWithPayload.getUserStep()) {
            case BUY_SELECT_PLAN -> {
                int planId = Integer.parseInt(callbackQueryData);
                currentPayload.setPlanId(planId);
                currentStepWithPayload.setUserStep(UserStep.BUY_PAYMENT_METHOD);
                userStepService.set(chatId, currentStepWithPayload);

                BuyAccountSelectPaymentMethodPage buyAccountSelectPaymentMethodPage = new BuyAccountSelectPaymentMethodPage();
                buyAccountSelectPaymentMethodPage.setChatId(chatId);
                buyAccountSelectPaymentMethodPage.setToken(this.config.getToken());
                this.requestHandler.send(buyAccountSelectPaymentMethodPage, Message.class);
            }

            case BUY_PAYMENT_METHOD -> {
                PaymentMethod paymentMethod = PaymentMethod.valueOf(update.getCallbackQuery().getData());
                currentPayload.setPaymentMethod(paymentMethod);
                currentStepWithPayload.setPayload(currentPayload);
                switch (paymentMethod) {
                    case TRANSFER_MONEY -> {
                        WaitForReceiptPage waitForReceiptPage = new WaitForReceiptPage();
                        waitForReceiptPage.setChatId(chatId);
                        waitForReceiptPage.setToken(this.config.getToken());
                        currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_RECEIPT);
                        userStepService.set(chatId, currentStepWithPayload);
                        this.requestHandler.send(waitForReceiptPage, Message.class);
                    }
                    case COUPON -> {
                        currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_COUPON);
                        WaitForCouponPage waitForCouponPage = new WaitForCouponPage();
                        waitForCouponPage.setChatId(chatId);
                        waitForCouponPage.setToken(this.config.getToken());
                        userStepService.set(chatId, currentStepWithPayload);

                        this.requestHandler.send(waitForCouponPage, Message.class);

                    }
                    case WALLET -> {
                        currentStepWithPayload.setUserStep(UserStep.BUY_CONFIRMATION);
                        userStepService.set(chatId, currentStepWithPayload);

                    }
                }
            }

            case BUY_WAIT_FOR_RECEIPT -> {

                Optional<Plan> plan = planRepository.findById(currentPayload.getPlanId());
                if (plan.isEmpty()) {
                    throw new RuntimeException("Plan id " + currentPayload.getPlanId() + " not found");
                }


                Order order = new Order();
                order.setPlan(plan.get());
                order.setStatus(OrderStatus.PENDING);
                order.setUser(user);
                order.setCreatedAt(new Date());
                order = orderRepository.save(order);

                // store photos
                for (PhotoSize photoSize : update.getMessage().getPhotos()) {
                    Photo photo = new Photo();
                    photo.setWidth(photoSize.getWidth());
                    photo.setHeight(photoSize.getHeight());
                    photo.setFileSize(photoSize.getFileSize());
                    photo.setTelegramFileId(photoSize.getFileId());
                    photo.setOrderId(order.getOrderId());
                    photo.setCreatedAt(new Date());
                    photoRepository.save(photo);
                }

                SendMessageMethod sendMessageMethod = new SendMessageMethod();
                sendMessageMethod.setText("با تشکر از ارسال تصویر فیش وایری. پس از تایید فیش توسط ادمین. کانفیگ برای شما ارسال می شود");
                sendMessageMethod.setChatId(update.getMessage().getChat().getId());
                sendMessageMethod.setToken(this.config.getToken());

                userStepService.clear(chatId);
                this.requestHandler.send(sendMessageMethod, Message.class);
            }
        }

    }
}
