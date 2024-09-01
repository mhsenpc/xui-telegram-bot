package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.config.XUIConfigBuilder;
import com.mhsenpc.v2raybot.bot.entity.TestConfig;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.repository.TestConfigRepository;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.*;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import com.mhsenpc.v2raybot.xui.services.VPNConfigBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TestAccountController extends TelegramController{

    @Autowired
    private TestClientDirector testClientDirector;

    @Autowired
    private VPNConfigBuilder vpnConfigBuilder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private NewTestAccountNotifier newTestAccountNotifier;

    @Autowired
    private XUIConfigBuilder xuiConfigBuilder;

    @Autowired
    private TestAccountLimit testAccountLimit;

    @Autowired
    private TestConfigRepository testConfigRepository;

    @Override
    public void invoke(Update update) {

        if(testAccountLimit.isTestAccountLimitReached(chatId)){
            this.sendMessage("متاسفانه به دلیل محدودیت های موجود. در حال حاضر ساخت اکانت تست بیشتر برای شماامکانپذیر نیست");
            return;
        }

        XUIClient XUIClient = null;

        try {
            XUIClient = testClientDirector.build();
            String configUrl = generateUrlForClient(XUIClient);
            TestConfig testConfig = storeTestConfig(configUrl);
            sendClientDetails(configUrl);
            notifyAdmins(testConfig);
        } catch (Exception e) {
            this.sendMessage("متاسفانه در فرآیند ساخت اکانت تست یک مشکل فنی به وجود آمده است");
            log.error(e.getMessage());
        }

    }

    private void notifyAdmins(TestConfig testConfig) {

        User user = testConfig.getUser();
        //if(user.isNormal()){
            newTestAccountNotifier.notifyAdmins(testConfig);
        //}
    }

    private String generateUrlForClient(XUIClient xuiClient) throws InboundNotRetrievedException {

        return vpnConfigBuilder
                .setClient(xuiClient)
                .setXUIConfig(xuiConfigBuilder.build())
                .build();
    }

    private TestConfig storeTestConfig(String configUrl) {

        User user = userRepository.findByChatId(chatId);

        TestConfig testConfig = new TestConfig();
        testConfig.setUrl(configUrl);
        testConfig.setCreatedAt(new Date());
        testConfig.setUser(user);
        testConfigRepository.save(testConfig);
        return testConfig;
    }

    private void sendClientDetails(String configUrl) {

        String qrCodeUrl = qrCodeService.getQRCodeUrl(configUrl);
        this.sendMessage(" اکانت تست با موفقیت ساخته شد");

        messageService.sendPhoto(chatId, qrCodeUrl, configUrl);
    }
}
