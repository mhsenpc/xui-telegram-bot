package com.mhsenpc.v2raybot.bot.pages.admin;

import com.mhsenpc.v2raybot.telegram.dto.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.dto.ReplyKeyboardMarkup;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;

public class HomePage extends SendMessageRequest {
    public HomePage() {
        setText("کاربر گرامی. به ربات خرید وی پی ان خوش آمدید");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton("خرید کانفیگ"), new KeyboardButton("کانفیگ های من"));
        replyKeyboardMarkup.addRow(new KeyboardButton("دریافت اکانت تست"));
        replyKeyboardMarkup.addRow(new KeyboardButton("شارژ کیف پول"), new KeyboardButton("وضعیت کانفیگ"));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
