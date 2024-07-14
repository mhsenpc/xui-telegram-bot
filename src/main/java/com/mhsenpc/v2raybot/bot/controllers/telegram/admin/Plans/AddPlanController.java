package com.mhsenpc.v2raybot.bot.controllers.telegram.admin.Plans;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.CreatePlanRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.admin.ViewPlansPage;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPlanController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public void invoke(Update update) {

        if (message.equals(ViewPlansPage.BTN_ADD_PLAN)) {

            userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_CREATE_PLAN_WAIT_FOR_MONTH, new CreatePlanRequest()));
            sendMessage("تعرفه چند ماهه تعریف می کنید؟ لطفا تعداد ماه را به عدد وارد کنید");
            return;
        }

        CreatePlanRequest currentPayload = (CreatePlanRequest) currentStepWithPayload.getPayload();
        switch (currentStepWithPayload.getUserStep()) {

            case ADMIN_CREATE_PLAN_WAIT_FOR_MONTH -> {
                try {
                    int months = Integer.parseInt(message);

                    currentPayload.setMonths(months);

                    currentStepWithPayload.setUserStep(UserStep.ADMIN_CREATE_PLAN_WAIT_FOR_TRAFFIC);
                    userStepService.set(chatId, currentStepWithPayload);
                    sendMessage("میزان ترافیک این تعرفه (به واحد گیگابایت) چقدر باشید؟ لطفا فقط عدد وارد کنید");
                }
                catch (NumberFormatException exception){
                    sendMessage("لطفا تعداد ماه ها را بصورت صحیح وارد کنید");
                }

            }
            case ADMIN_CREATE_PLAN_WAIT_FOR_TRAFFIC -> {

                try {
                    int connections = Integer.parseInt(message);

                    currentPayload.setTrafficLimit(connections);

                    currentStepWithPayload.setUserStep(UserStep.ADMIN_CREATE_PLAN_WAIT_FOR_CONNECTIONS);
                    userStepService.set(chatId, currentStepWithPayload);
                    sendMessage("چه تعداد کاربر بصورت همزمان مجاز به استفاده از سرویس شما هستند؟ فقط عدد");
                }
                catch (NumberFormatException exception){
                    sendMessage("لطفا میزان ترافیک مجاز را بصورت صحیح به گیگابایت بنویسید");
                }
            }
            case ADMIN_CREATE_PLAN_WAIT_FOR_CONNECTIONS -> {

                try {
                    int connections = Integer.parseInt(message);

                    currentPayload.setConnectionLimit(connections);

                    currentStepWithPayload.setUserStep(UserStep.ADMIN_CREATE_PLAN_WAIT_FOR_PRICE);
                    userStepService.set(chatId, currentStepWithPayload);
                    sendMessage("لطفا قیمت این تعرفه را به تومان وارد کنید. فقط عدد بدون نقطه و ویرگول");
                }
                catch (NumberFormatException exception){
                    sendMessage("لطفا تعداد کاربر همزمان را به درستی وارد کنید");
                }
            }
            case ADMIN_CREATE_PLAN_WAIT_FOR_PRICE -> {
                try{
                    int price = Integer.parseInt(message);

                    currentPayload.setPrice(price);

                    Plan plan = new Plan();
                    plan.setMonths(currentPayload.getMonths());
                    plan.setTrafficLimit(currentPayload.getTrafficLimit());
                    plan.setConnectionLimit(currentPayload.getConnectionLimit());
                    plan.setPrice(currentPayload.getPrice());
                    planRepository.save(plan);

                    userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_PLANS));
                    sendMessage("تعرفه با موفقیت ذخیره شد");
                }
                catch (NumberFormatException exception){
                    sendMessage("لطفا قیمت را بصورت صحیح وارد کنید");
                }
            }
        }

    }
}
