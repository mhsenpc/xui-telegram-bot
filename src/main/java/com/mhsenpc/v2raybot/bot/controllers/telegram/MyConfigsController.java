package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.entity.Client;
import com.mhsenpc.v2raybot.bot.entity.TestConfig;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyConfigsController extends TelegramController {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void invoke(Update update) {
        User dbUser = userRepository.findByChatId(this.chatId);
        printRealConfigs(dbUser);
        printTestConfigs(dbUser);
    }

    private void printTestConfigs(User dbUser) {

        for(TestConfig testConfig: dbUser.getTestConfigs()){
            String text = "اکانت تست: " + System.lineSeparator();
            text += testConfig.getUrl() + System.lineSeparator();
            sendMessage(text);
        }
    }

    private void printRealConfigs(User dbUser) {

        for(Client client: dbUser.getClients()){
            String text = client.getOrder().getPlan().getMonths() + "ماهه " + System.lineSeparator();
            text += client.getOrder().getPlan().getTrafficLimit() + "گیگ " + System.lineSeparator();
            if(client.getValidUntil().after(new Date())){
                text += "منقضی شده است" + System.lineSeparator();
            }
            else{
                text += "اعتبار زمانی دارد" + System.lineSeparator();
            }
            text += client.getUrl();
            sendMessage(text);
        }
    }
}
