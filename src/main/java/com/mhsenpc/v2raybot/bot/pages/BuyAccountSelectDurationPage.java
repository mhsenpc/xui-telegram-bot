package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.dto.ReplyKeyboardMarkup;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;

public class BuyAccountSelectDurationPage extends SendMessageRequest {
    public BuyAccountSelectDurationPage() {

        setText("به اکانت چند ماهه نیاز دارید؟");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton("یک ماهه"));
        replyKeyboardMarkup.addRow(new KeyboardButton("دو ماهه"));
        replyKeyboardMarkup.addRow(new KeyboardButton("سه ماهه"));
        replyKeyboardMarkup.addRow(new KeyboardButton("بازگشت"));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
