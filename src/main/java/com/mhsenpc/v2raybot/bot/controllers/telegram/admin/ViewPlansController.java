package com.mhsenpc.v2raybot.bot.controllers.telegram.admin;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.PlanItemButtonCallback;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.PlanCommandType;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.admin.ViewPlansPage;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.PlanFormatter;
import com.mhsenpc.v2raybot.bot.services.PlanItemButtonCallbackSerializer;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewPlansController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanFormatter planFormatter;

    @Override
    public void invoke(Update update) throws Exception {

        User user = this.userRepository.findByChatId(chatId);

        if(user.getRole() != UserRole.ADMIN.getValue()){
            sendMessage("شما اجازه دسترسی به این بخش را ندارید. لطفا با مدیر سیستم تماس بگیرید");
            return;
        }

        userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_PLANS));

        switch (message){
            case ViewPlansPage.BTN_ADD_PLAN -> {
                sendMessage("این قسمت هنوز فعال نشده است");
            }
            default -> {

                ViewPlansPage viewPlansPage = new ViewPlansPage();
                viewPlansPage.setChatId(chatId);
                viewPlansPage.setText("لیست تعرفه ها");

                this.requestHandler.send(viewPlansPage, Message.class);


                List<Plan> plans = planRepository.findAllNonDeletedPlans();
                if(plans.isEmpty()){
                    this.sendMessage("تاکنون هیچ تعرفه ای ثبت نشده است. بدون ثبت تعرفه کاربران توانایی ثبت سفارش ندارند");
                }
                for (Plan plan: plans){
                    SendMessageMethod planItemMessage = new SendMessageMethod();
                    planItemMessage.setChatId(chatId);
                    planItemMessage.setText(planFormatter.format(plan));

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    String removePlanCallbackData = PlanItemButtonCallbackSerializer.serialize(new PlanItemButtonCallback(plan.getPlanId(), PlanCommandType.REMOVE));

                    inlineKeyboardMarkup.addRow(
                            new InlineKeyboardButton("حذف", removePlanCallbackData)
                    );

                    planItemMessage.setReplyMarkup(inlineKeyboardMarkup);
                    requestHandler.send(planItemMessage, Message.class);
                }

                userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_WAITING_FOR_PLAN_COMMANDS));
            }
        }
    }
}
