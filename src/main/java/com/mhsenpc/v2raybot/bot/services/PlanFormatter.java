package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Plan;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class PlanFormatter {

    public String format(Plan plan){

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String formattedNumber = numberFormat.format(plan.getPrice());

        return  plan.getMonths() + " ماهه" + "\n" +
                plan.getTrafficLimit() + " گیگ" + "\n" +
                plan.getConnectionLimit() + " کاربره" + "\n" +
                formattedNumber +  " تومان" ;

    }
}
