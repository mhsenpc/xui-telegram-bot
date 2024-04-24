package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.controllers.BaseController;
import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Photo;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.*;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.PhotoRepository;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.PhotoSize;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class BuyController extends BaseController implements ITelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyAccountSelectPlanPage buyAccountSelectPlanPage;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private RequestHandler requestHandler;

    @Override
    public void invoke(Update update) {

        // we need to detect what is current step of user
        String chatId = "";
        String message = "";
        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            message = Optional.ofNullable(update.getCallbackQuery().getMessage().getText()).orElse(message);
            chatId = update.getCallbackQuery().getFrom().getId();
            callbackQueryData = update.getCallbackQuery().getData();
        } else if (update.getMessage() != null) {
            message = Optional.ofNullable(update.getMessage().getText()).orElse(message);
            chatId = update.getMessage().getFrom().getId();

        }
        UserStepWithPayload currentStepWithPayload = userStepService.get(chatId);
        User user = this.userRepository.findByChatId(chatId);

        if (message.equals(HomePage.BTN_BUY_CONFIG)) {
            BuyAccountRequest buyAccountRequest = new BuyAccountRequest();
            buyAccountRequest.setChatId(chatId);
            buyAccountRequest.setUserId(user.getUserId());
            UserStepWithPayload stepWithPayload = new UserStepWithPayload(UserStep.BUY_SELECT_PLAN, buyAccountRequest);
            userStepService.set(chatId, stepWithPayload);

            buyAccountSelectPlanPage.setChatId(chatId);
            buyAccountSelectPlanPage.setToken(this.config.getToken());

            this.requestHandler.send(buyAccountSelectPlanPage, Message.class);
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
                switch (paymentMethod) {
                    case TRANSFER_MONEY -> {
                        currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_RECEIPT);
                        WaitForReceiptPage waitForReceiptPage = new WaitForReceiptPage();
                        waitForReceiptPage.setChatId(chatId);
                        waitForReceiptPage.setToken(this.config.getToken());
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
                order.setStatus(OrderStatus.PENDING.getValue());
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
