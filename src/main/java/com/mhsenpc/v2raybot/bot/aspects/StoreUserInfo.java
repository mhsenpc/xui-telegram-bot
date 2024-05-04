package com.mhsenpc.v2raybot.bot.aspects;

import com.mhsenpc.v2raybot.bot.entity.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStatus;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.types.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class StoreUserInfo {

    @Autowired
    UserRepository userRepository;

    @Before("execution(* com.mhsenpc.v2raybot.bot.controllers.TelegramRequestController.handleRequests(*))")
    private void storeUserInfoBeforeHandlingRequest(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        Update update = (Update) args[0];

        User telegramUser = null;
        if(update.getMessage() != null){
            telegramUser = update.getMessage().getFrom();
        }
        else if(update.getCallbackQuery() != null){
            telegramUser = update.getCallbackQuery().getFrom();
        }

        if(telegramUser != null){
            storeTelegramUserToDB(telegramUser);
        }
    }

    private void storeTelegramUserToDB(User telegramUser) {

        var dbUser = userRepository.findByChatId(telegramUser.getId());
        if(dbUser != null){
            return;
        }

        dbUser = new com.mhsenpc.v2raybot.bot.entity.User();
        dbUser.setFirstName(telegramUser.getFirstName());
        dbUser.setLastName(telegramUser.getLastName());
        dbUser.setUsername(telegramUser.getUsername());
        dbUser.setChatId(telegramUser.getId());
        dbUser.setStatus(UserStatus.ACTIVE);
        dbUser.setCreatedAt(new Date());
        dbUser.addRole(new UserRole(com.mhsenpc.v2raybot.bot.enums.UserRole.NORMAL));
        userRepository.save(dbUser);
    }
}
