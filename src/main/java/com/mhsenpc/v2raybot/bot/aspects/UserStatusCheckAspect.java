package com.mhsenpc.v2raybot.bot.aspects;

import com.mhsenpc.v2raybot.bot.enums.UserStatus;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.MessageService;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.types.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserStatusCheckAspect {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @Around("execution(* com.mhsenpc.v2raybot.bot.controllers.TelegramRequestController.handleRequests(*))")
    public Object checkUserStatus(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Update update = (Update) args[0];

        User telegramUser = null;
        if(update.getMessage() != null){
            telegramUser = update.getMessage().getFrom();
        }
        else if(update.getCallbackQuery() != null){
            telegramUser = update.getCallbackQuery().getFrom();
        }

        if(telegramUser != null) {
            var dbUser = userRepository.findByChatId(telegramUser.getId());
            if (dbUser != null && dbUser.getStatus() == UserStatus.INACTIVE.getValue()) {
                messageService.send(telegramUser.getId(), "حساب کاربری شما مسدود شده است");
                return null;
            }
        }

        return joinPoint.proceed();
    }
}
