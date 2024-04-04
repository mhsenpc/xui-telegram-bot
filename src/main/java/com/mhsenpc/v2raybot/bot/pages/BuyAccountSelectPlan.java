package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.*;

public class BuyAccountSelectPlan extends BasePage {

    public static final String BTN_BACK = "بازگشت";

    public BuyAccountSelectPlan(int chatId) {
        super(chatId);

        setText("کدام پلن را  انتخاب می کنید؟");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        // todo: load from plans table
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("یک ماهه ۱۰۰ گیگ ۳۰ هزار تومن", "a"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("wewe", "b"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("asdasd", "c"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_BACK, "e"));
        this.setReplyMarkup(inlineKeyboardMarkup);
    }
}
