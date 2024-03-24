package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.dto.ReplyKeyboardMarkup;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;

public class BuyAccountSelectTraffic extends SendMessageRequest {
    public BuyAccountSelectTraffic() {

        setText("کدام پلن را  انتخاب می کنید؟");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton("۲۰ گیگ ۹۰ هزار تومن"));
        replyKeyboardMarkup.addRow(new KeyboardButton("۴۰ گیگ ۹۰ هزار تومن"));
        replyKeyboardMarkup.addRow(new KeyboardButton("۶۰ گیگ ۱۹۰ هزار تومن"));
        replyKeyboardMarkup.addRow(new KeyboardButton("بازگشت"));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
