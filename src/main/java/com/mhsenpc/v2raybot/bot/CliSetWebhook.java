package com.mhsenpc.v2raybot.bot;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.telegram.methods.SetWebhookMethodBase;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.SetWebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CliSetWebhook implements ApplicationRunner {

    @Autowired
    RequestHandler requestHandler;

    @Autowired
    private ConfigurationManager configurationManager;

    @Override
    public void run(ApplicationArguments args) {

        if (args.containsOption("webhook")) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter bot url: ");
            String url = scanner.nextLine();


            Config config = configurationManager.getConfig();

            if(!url.endsWith("/")){
                url += "/";
            }

            SetWebhookMethodBase setWebhookMethod = new SetWebhookMethodBase();
            setWebhookMethod.addQueryParam("url", url + "handle");
            setWebhookMethod.setToken(config.getToken());

            try {
                SetWebhookResponse response = requestHandler.send(setWebhookMethod, SetWebhookResponse.class);
                System.out.println( "Success. webhook set");;
            }
            catch (Exception exception){
                System.out.println( "Failed to set webhook" + System.lineSeparator() + exception.getMessage()
                        + "Token: " + config.getToken()
                        + "Url: " + url);;
            }


            System.exit(0);
        }
    }
}
