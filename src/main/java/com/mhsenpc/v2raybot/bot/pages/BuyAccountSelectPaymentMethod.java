package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.dto.ReplyKeyboardMarkup;

public class BuyAccountSelectPaymentMethod extends BasePage {

    public static final String BTN_TRANSFER_MONEY = "کارت به کارت";
    public static final String BTN_USE_BALANCE = "پرداخت با کیف پول";

    public BuyAccountSelectPaymentMethod(int chatId) {
        super(chatId);
        setText("مبلغ را چگونه پرداخت می کنید؟");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_TRANSFER_MONEY));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_USE_BALANCE));
        replyKeyboardMarkup.addRow(new KeyboardButton(BasePage.BTN_BACK));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
