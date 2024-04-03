package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.*;

public class BuyAccountSelectTraffic extends SendMessageRequest {

    public static final String BTN_BACK = "بازگشت";

    public BuyAccountSelectTraffic() {

        setText("کدام پلن را  انتخاب می کنید؟");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        // todo: load from plans table
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("۲۰ گیگ ۹۰ هزار تومن", "20"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("۴۰ گیگ ۹۰ هزار تومن", "40"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("۶۰ گیگ ۱۹۰ هزار تومن", "60"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_BACK));
        this.setReplyMarkup(inlineKeyboardMarkup);
    }
}
