package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BuyAccountSelectPlan extends Page {

    @Autowired
    public BuyAccountSelectPlan(PlanRepository planRepository) {

        setText("کدام پلن را  انتخاب می کنید؟");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        for(Plan plan: planRepository.findAll()){
            inlineKeyboardMarkup.addRow(new InlineKeyboardButton(plan.getTitle(), String.valueOf(plan.getPlanId())));
        }
        this.setReplyMarkup(inlineKeyboardMarkup);
    }
}
