package com.mhsenpc.v2raybot.bot.services;

import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class NumberFormatter {

    public String format(float number){

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(number);
    }
}
