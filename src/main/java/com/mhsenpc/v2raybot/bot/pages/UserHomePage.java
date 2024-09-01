package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.ReplyKeyboardMarkup;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;

public class UserHomePage extends SendMessageMethod {

    public static final String BTN_BUY_CONFIG = "خرید کانفیگ";
    public static final String BTN_MY_CONFIGS = "کانفیگ های من";
    public static final String BTN_TEST_ACCOUNT = "دریافت اکانت تست";

    public UserHomePage() {

        setText("کاربر گرامی. به ربات خرید وی پی ان خوش آمدید");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        new KeyboardButton(BTN_TEST_ACCOUNT);
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_MY_CONFIGS), new KeyboardButton(BTN_BUY_CONFIG));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}