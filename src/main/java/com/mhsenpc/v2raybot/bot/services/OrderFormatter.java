package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderFormatter {

    public String getFormattedOrder(Order order){

        return order.getPlan().getTitle() + order.getUser().getUsername();
    }
}
