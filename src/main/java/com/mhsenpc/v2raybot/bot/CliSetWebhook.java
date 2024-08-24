package com.mhsenpc.v2raybot.bot;

import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import com.mhsenpc.v2raybot.telegram.methods.SetWebhookMethodBase;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.SetWebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CliSetWebhook implements ApplicationRunner {

    @Autowired
    RequestHandler requestHandler;

    @Autowired
    private ConfigurationManager configurationManager;

    @Value("${github.actions:false}")
    private boolean githubActions;

    @Override
    public void run(ApplicationArguments args) {

        if(githubActions){
            return;
        }

        String token = configurationManager.getConfig(ConfigName.BOT_TOKEN);
        if(token.isEmpty()){
            throw new RuntimeException("Bot token was not set in config");
        }

        String botHostUrl = configurationManager.getConfig(ConfigName.BOT_HOST_URL);

        if(!botHostUrl.endsWith("/")){
            botHostUrl += "/";
        }

        SetWebhookMethodBase setWebhookMethod = new SetWebhookMethodBase();
        setWebhookMethod.addQueryParam("url", botHostUrl + "handle");
        setWebhookMethod.setToken(token);

        try {
            SetWebhookResponse response = requestHandler.send(setWebhookMethod, SetWebhookResponse.class);
            System.out.println("api response:" + response.getDescription());
            System.out.println( "Success. webhook set. Telegram request will be sent to " + botHostUrl);;
        }
        catch (Exception exception){
            System.out.println( "Failed to set webhook" + System.lineSeparator() + exception.getMessage()
                    + "Token: " + token
                    + "Url: " + botHostUrl);

            System.out.println("Please consider check BOT_TOKEN and BOT_HOST_URL in your config!");

            System.exit(1);
        }

    }
}
