package com.xui.telegram.xui.services;

import com.xui.telegram.xui.exceptions.CookieNotFoundException;
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

    public String getCookie() throws CookieNotFoundException {
        try {
            return textReader.readAllText(cookieFileName);
        }
        catch (IOException exception){
            throw new CookieNotFoundException();
        }
    }
}
