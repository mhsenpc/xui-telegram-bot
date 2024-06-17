package com.mhsenpc.v2raybot.bot;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
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
            Config config = new Config();

            System.out.print("Enter Bot token: ");
            config.setToken(scanner.nextLine());

            System.out.print("Enter panel URL: ");
            config.setBaseUrl(scanner.nextLine());

            System.out.print("Enter panel username: ");
            config.setUsername(scanner.nextLine());

            System.out.print("Enter panel password: ");
            config.setPassword(scanner.nextLine());

            configurationManager.setConfig(config);

            System.out.println("config saved successfully");

            System.exit(0);
        }
    }
}
