package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.repository.TestConfigRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestAccountLimit {

    @Autowired
    private TestConfigRepository testConfigRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean isTestAccountLimitReached(String chatId){

        User user = userRepository.findByChatId(chatId);
//        if(user.getRole() == UserRole.ADMIN.getValue()){
//            return false;
//        }

        int testConfigsCount = testConfigRepository.countByUserIdAndCreatedAtAfter(user.getUserId(), LocalDateTime.now().minusDays(10));
        return testConfigsCount > 0;
    }
}
