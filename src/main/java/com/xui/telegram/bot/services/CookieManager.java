package com.xui.telegram.bot.services;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class CookieManager {

    TextReader textReader = new TextReader();
    private String cookieFileName = "storage/panel.cookie";

    public void setCookie(String cookie) throws IOException {

        FileWriter writer = new FileWriter(cookieFileName);
        writer.write(cookie);
        writer.close();
        System.out.println("Successfully wrote to the file: " + cookieFileName);
    }

    public String getCookie() throws IOException {
        return textReader.readAllText(cookieFileName);
    }
}
