package com.mhsenpc.v2raybot.bot.controllers.telegram.admin;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.PlanItemButtonCallback;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.enums.PlanCommandType;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.bot.services.PlanItemButtonCallbackSerializer;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.types.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class HandlePlansController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public void invoke(Update update) {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            try {
                callbackQueryData = update.getCallbackQuery().getData();

                PlanItemButtonCallback response = PlanItemButtonCallbackSerializer.deserialize(callbackQueryData);
                this.handleButtonCallback(response);

            } catch (Exception e) {
                this.sendMessage("متاسفانه مشکلی در دریافت فرمان شما پیش آمده است");
                log.error(e.getMessage());
            }

            userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_PLANS));

        }

    }

    private void handleButtonCallback(PlanItemButtonCallback response) {

        if(response.getCommandType() == PlanCommandType.REMOVE) {

            Optional<Plan> plan = planRepository.findById(response.getPlanId());
            if(plan.isEmpty()){
                sendMessage("این تعرفه وجود ندارد");
                return;
            }

            Plan planToDelete = plan.get();
            planToDelete.setDeletedAt(new Date());

            planRepository.save(planToDelete);
            sendMessage("تعرفه با موفقیت حذف شد");
        }
    }
}
