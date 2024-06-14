package com.mhsenpc.v2raybot.bot.pages.admin;

import com.mhsenpc.v2raybot.bot.pages.UserHomePage;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.ReplyKeyboardMarkup;

public class AdminHomePage extends SendMessageMethod {

    public static final String BTN_VIEW_ORDERS = "سفارشات";


    public AdminHomePage() {

        setText("مدیر  گرامی. به ربات خرید وی پی ان خوش آمدید");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(UserHomePage.BTN_TEST_ACCOUNT));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_VIEW_ORDERS));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}