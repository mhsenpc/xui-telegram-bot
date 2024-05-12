package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Plan;
import org.springframework.stereotype.Service;

@Service
public class PlanFormatter {

    public String format(Plan plan){

        return  plan.getMonths() + " ماهه" + "\n" +
                plan.getTrafficLimit() + " گیگ" + "\n" +
                plan.getConnectionLimit() + " کاربره" + "\n" +
                plan.getPrice() +  " تومان" ;

    }
}
