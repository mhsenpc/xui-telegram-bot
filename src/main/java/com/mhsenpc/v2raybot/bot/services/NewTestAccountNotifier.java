package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.TestConfig;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewTestAccountNotifier {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private OrderFormatter orderFormatter;

    @Autowired
    private MessageService messageService;

    public void notifyAdmins(TestConfig testConfig){

        List<User> admins = userRepository.getAdmins();

        for(User admin: admins){
            messageService.send(admin.getChatId(), getMessage(testConfig));
        }
    }

    private String getMessage(TestConfig testConfig){

        return "یک اکانت تست توسط "+ testConfig.getUser().getFirstName() + " " + testConfig.getUser().getLastName() + " ساخته شد" + System.lineSeparator();
    }
}
