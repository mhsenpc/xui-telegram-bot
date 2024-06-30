package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Photo;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.UserHomePage;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.PhotoRepository;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.NewOrderNotifier;
import com.mhsenpc.v2raybot.bot.services.NumberFormatter;
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

    @Autowired
    private NewOrderNotifier newOrderNotifier;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private NumberFormatter numberFormatter;

    @Override
    public void invoke(Update update) {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            callbackQueryData = update.getCallbackQuery().getData();
        }
        User user = this.userRepository.findByChatId(chatId);

        if (message.equals(UserHomePage.BTN_BUY_CONFIG)) {

            SendMessageMethod planMessageItem = new SendMessageMethod();
            planMessageItem.setChatId(chatId);

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
                buyAccountRequestPayload.setPaymentMethod(PaymentMethod.WALLET);
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
                Optional<Plan> plan = planRepository.findById(planId);
                if(plan.isEmpty()){
                    throw new RuntimeException("تعرفه انتخاب شده معتبر نیست");
                }

                currentPayload.setPlanId(planId);

                currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_RECEIPT);
                userStepService.set(chatId, currentStepWithPayload);

                String formattedNumber = numberFormatter.format(plan.get().getPrice());
                sendMessage("سفارش شما با موفقیت ثبت شد. هزینه کانفیگ "  +  formattedNumber + " تومان");

                String customPostOrderMessage = configurationManager.getConfig(ConfigName.SAVE_ORDER_MESSAGE);
                if(!customPostOrderMessage.isEmpty()) {
                    sendMessage(customPostOrderMessage);
                }
            }

            case BUY_WAIT_FOR_RECEIPT -> {

                Optional<Plan> plan = planRepository.findById(currentPayload.getPlanId());
                if (plan.isEmpty()) {
                    sendMessage("تعرفه انتخاب شده معتبر نیست");
                    throw new RuntimeException("Plan id " + currentPayload.getPlanId() + " not found");
                }

                if(update.getMessage() == null){
                    sendMessage("پیام ارسال شده معتبر نیست");
                    return;
                }

                if(update.getMessage().getPhotos() == null || update.getMessage().getPhotos().isEmpty()){
                    sendMessage("لطفا فیش واریز شده را ارسال نمایید");
                    return;
                }

                Order order = new Order();
                order.setPlan(plan.get());
                order.setStatus(OrderStatus.PENDING);
                order.setUser(user);
                order.setPaymentMethod(currentPayload.getPaymentMethod());
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

                userStepService.clear(chatId);
                this.requestHandler.send(sendMessageMethod, Message.class);

                newOrderNotifier.notify(order);
            }
        }

    }
}
