package com.mhsenpc.v2raybot.bot.services;

import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class QRCodeService {

    public String getQRCodeUrl(String content) {


        String encodedUrl = URLEncoder.encode(content, StandardCharsets.UTF_8);
        return "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=" + encodedUrl;
    }
}
