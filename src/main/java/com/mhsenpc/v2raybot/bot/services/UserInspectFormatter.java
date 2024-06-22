package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInspectFormatter {

    @Autowired
    private UserFormatter userFormatter;

    public String getFormattedUser(User user){

        String pattern = "%s" + System.lineSeparator()
                + "Clients: %s " +  System.lineSeparator()
                + "Tests: %s " +  System.lineSeparator()
                + "Orders: %s " +  System.lineSeparator();


        return String.format(
                pattern,
                userFormatter.getFormattedUser(user),
                user.getClients().size(),
                user.getTestConfigs().size(),
                user.getOrders().size()
        );
    }
}
