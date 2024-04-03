package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.dto.ReplyKeyboardMarkup;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;

public class BuyAccountSelectDurationPage extends SendMessageRequest {

    public static final String BTN_1_MONTH = "1";
    public static final String BTN_2_MONTH = "2";
    public static final String BTN_3_MONTH = "3";
    public static final String BTN_BACK = "بازگشت";

    public BuyAccountSelectDurationPage() {

        setText("به اکانت چند ماهه نیاز دارید؟");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_1_MONTH));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_2_MONTH));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_3_MONTH));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_BACK));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
