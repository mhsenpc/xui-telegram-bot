package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class UserFormatter {

    public String getFormattedUser(User user){

        String pattern = "%s %s(%s %s) %s";

        String userStatusIcon = "";
        if(user.getStatus() == UserStatus.ACTIVE.getValue()){
            userStatusIcon = "✅";
        } else if (user.getStatus() == UserStatus.INACTIVE.getValue()) {
            userStatusIcon = "🚧";
        }

        String userRoleIcon = "";
        if(user.getRole() == UserRole.ADMIN.getValue()){
            userRoleIcon = "🫅";
        }

        return String.format(pattern, userStatusIcon, user.getUsername(), user.getFirstName(), user.getLastName(), userRoleIcon);
    }
}
