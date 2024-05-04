package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import org.springframework.stereotype.Service;

@Service
public class OrderFormatter {

    public String getFormattedOrder(Order order){

        String text = "";
        text += "%s ماهه" + System.lineSeparator();
        text += "%s گیگ" + System.lineSeparator();
        text += "کاربره %s" + System.lineSeparator();
        text += "خریدار: %s" + System.lineSeparator();
        text += "%s تومن" + System.lineSeparator();

        Plan plan = order.getPlan();
        return String.format(text, plan.getMonths(), plan.getTrafficLimit(), plan.getConnectionLimit(), order.getUser().getUsername(), plan.getPrice());
    }
}
