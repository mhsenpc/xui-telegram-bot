package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.entity.TestConfig;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.MessageService;
import com.mhsenpc.v2raybot.bot.services.QRCodeService;
import com.mhsenpc.v2raybot.bot.services.TestClientDirector;
import com.mhsenpc.v2raybot.bot.services.XuiConfigAdapter;
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
    private ConfigurationManager configurationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private MessageService messageService;

    @Override
    public void invoke(Update update) {

        XUIClient XUIClient = null;
        try {
            XUIClient = testClientDirector.build();
            String configUrl = generateUrlForClient(XUIClient);
            storeTestConfig(configUrl);
            sendClientDetails(configUrl);
        } catch (Exception e) {
            this.sendMessage("متاسفانه در فرآیند ساخت اکانت تست یک مشکل فنی به وجود آمده است");
            log.error(e.getMessage());
        }

    }

    private String generateUrlForClient(XUIClient xuiClient) throws InboundNotRetrievedException {

        XuiConfigAdapter configAdapter = new XuiConfigAdapter(configurationManager.getConfig());
        return vpnConfigBuilder
                .setClient(xuiClient)
                .setXUIConfig(configAdapter)
                .build();
    }

    private void storeTestConfig(String configUrl) {

        User user = userRepository.findByChatId(chatId);

        TestConfig testConfig = new TestConfig();
        testConfig.setUrl(configUrl);
        testConfig.setCreatedAt(new Date());
        user.addTestConfig(testConfig);
        userRepository.save(user);
    }

    private void sendClientDetails(String configUrl) {

        String qrCodeUrl = qrCodeService.getQRCodeUrl(configUrl);
        this.sendMessage(" اکانت تست با موفقیت ساخته شد");

        messageService.sendPhoto(chatId, qrCodeUrl, configUrl);
    }
}
