package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.exceptions.CookieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
public class CookieManager {

    TextReader textReader = new TextReader();
    private String cookieFileName = "storage/panel.cookie";

    public void setCookie(String cookie) throws IOException {

        FileWriter writer = new FileWriter(cookieFileName);
        writer.write(cookie);
        writer.close();
        log.info("Successfully wrote to the file: " + cookieFileName);
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
