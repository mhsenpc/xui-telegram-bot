package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.pages.UserHomePage;
import com.mhsenpc.v2raybot.bot.pages.admin.AdminHomePage;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePageFactory {

    @Autowired
    private UserRepository userRepository;

    public SendMessageMethod getHomePage(User telegramUser){

        if(telegramUser != null){
            com.mhsenpc.v2raybot.bot.entity.User dbUser = userRepository.findByChatId(telegramUser.getId());
            if(dbUser == null){
                throw new RuntimeException("Couldn't find user in db");
            }

            if(dbUser.isAdmin()){
                return new AdminHomePage();
            } else if (dbUser.isNormal()) {
                return new UserHomePage();
            }
            throw new RuntimeException("Role type is not valid: " + dbUser.getRole());
        }
        throw new RuntimeException("Telegram user not provided");
    }
}
