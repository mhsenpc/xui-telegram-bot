package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.ReplyKeyboardMarkup;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;

public class HomePage extends SendMessageMethod {

    public static final String BTN_BUY_CONFIG = "خرید کانفیگ";
    public static final String BTN_MY_CONFIGS = "کانفیگ های من";
    public static final String BTN_TEST_ACCOUNT = "دریافت اکانت تست";
    public static final String BTN_CHARGE_ACCOUNT = "شارژ کیف پول";
    public static final String BTN_VIEW_BALANCE = "وضعیت کانفیگ";

    public HomePage() {
        setText("کاربر گرامی. به ربات خرید وی پی ان خوش آمدید");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_MY_CONFIGS), new KeyboardButton(BTN_BUY_CONFIG));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_TEST_ACCOUNT));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_VIEW_BALANCE), new KeyboardButton(BTN_CHARGE_ACCOUNT));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}