package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanFormatter {

    @Autowired
    private NumberFormatter numberFormatter;

    public String format(Plan plan){

        String formattedNumber = numberFormatter.format(plan.getPrice());

        return  plan.getMonths() + " ماهه" + "\n" +
                plan.getTrafficLimit() + " گیگ" + "\n" +
                plan.getConnectionLimit() + " کاربره" + "\n" +
                formattedNumber +  " تومان" ;

    }
}
