package com.mhsenpc.v2raybot.bot;

import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CliConfiguration implements ApplicationRunner {

    @Autowired
    private ConfigurationManager configurationManager;

    @Override
    public void run(ApplicationArguments args) {

        if (args.containsOption("config")) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Bot token: ");
            configurationManager.setConfig(ConfigName.BOT_TOKEN, scanner.nextLine());

            System.out.print("Enter panel URL: ");
            configurationManager.setConfig(ConfigName.PANEL_BASE_URL, scanner.nextLine());

            System.out.print("Enter panel username: ");
            configurationManager.setConfig(ConfigName.PANEL_USERNAME, scanner.nextLine());

            System.out.print("Enter panel password: ");
            configurationManager.setConfig(ConfigName.PANEL_PASSWORD, scanner.nextLine());

            System.out.println("config saved successfully");

            System.exit(0);
        }
    }
}
