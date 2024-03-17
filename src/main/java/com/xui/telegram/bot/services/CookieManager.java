package com.xui.telegram.bot.services;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class CookieManager {

    private String cookieFileName = "storage/panel.cookie";

    public void setCookie(String cookie) throws IOException {

        FileWriter writer = new FileWriter(cookieFileName);
        writer.write(cookie);
        System.out.println("Successfully wrote to the file: " + cookieFileName);
    }

    public String getCookie() {
        return "cookie should be returned";
    }

    public String getCookieFileName() {
        return cookieFileName;
    }

    public void setCookieFileName(String cookieFileName) {
        this.cookieFileName = cookieFileName;
    }
}
