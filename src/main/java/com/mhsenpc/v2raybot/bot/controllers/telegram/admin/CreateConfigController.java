package com.mhsenpc.v2raybot.bot.controllers.telegram.admin;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.admin.AdminHomePage;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.ConfirmOrderService;
import com.mhsenpc.v2raybot.bot.services.PlanFormatter;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CreateConfigController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanFormatter planFormatter;

    @Autowired
    private ConfirmOrderService confirmOrderService;

    @Override
    public void invoke(Update update) throws Exception {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            callbackQueryData = update.getCallbackQuery().getData();
        }
        User user = this.userRepository.findByChatId(chatId);

        if(user.getRole() != UserRole.ADMIN.getValue()){
            sendMessage("شما اجازه دسترسی به این بخش را ندارید. لطفا با مدیر سیستم تماس بگیرید");
            return;
        }

        if (message.equals(AdminHomePage.BTN_CREATE_CONFIG)) {

            SendMessageMethod planMessageItem = new SendMessageMethod();
            planMessageItem.setChatId(chatId);

            List<Plan> planList = planRepository.findAllNonDeletedPlans();
            if(planList.isEmpty()){
                planMessageItem.setText("متاسفانه هیچ تعرفه ای در سیستم تعریف نشده است. لطفا از بخش تنظیمات پلن های خود را تعریف کنید");
                this.requestHandler.send(planMessageItem, Message.class);
            }
            else {
                this.sendMessage("لطفا از بین تعرفه های زیر یک گزینه را انتخاب گنید");

                // set step
                BuyAccountRequest buyAccountRequestPayload = new BuyAccountRequest();
                buyAccountRequestPayload.setPaymentMethod(PaymentMethod.WALLET);
                UserStepWithPayload stepWithPayload = new UserStepWithPayload(UserStep.ADMIN_SELECT_PLAN, buyAccountRequestPayload);
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
        if (Objects.requireNonNull(currentStepWithPayload.getUserStep()) == UserStep.ADMIN_SELECT_PLAN) {
            int planId = Integer.parseInt(callbackQueryData);
            Optional<Plan> plan = planRepository.findById(planId);
            if (plan.isEmpty()) {
                throw new RuntimeException("تعرفه انتخاب شده معتبر نیست");
            }

            currentPayload.setPlanId(planId);

            currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_RECEIPT);
            userStepService.set(chatId, currentStepWithPayload);

            Order order = new Order();
            order.setPlan(plan.get());
            order.setStatus(OrderStatus.PENDING);
            order.setUser(user);
            order.setPaymentMethod(currentPayload.getPaymentMethod());
            order.setCreatedAt(new Date());
            order = orderRepository.save(order);

            confirmOrderService.confirm(order);
            userStepService.clear(chatId);
        }
    }
}
