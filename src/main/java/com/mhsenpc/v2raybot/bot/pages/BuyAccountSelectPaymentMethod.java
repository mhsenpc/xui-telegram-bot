package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.telegram.dto.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.dto.InlineKeyboardMarkup;

public class BuyAccountSelectPaymentMethod extends BasePage {

    public static final String BTN_TRANSFER_MONEY = "کارت به کارت";
    public static final String BTN_USE_BALANCE = "پرداخت با کیف پول";

    public BuyAccountSelectPaymentMethod() {
        setText("مبلغ را چگونه پرداخت می کنید؟");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_TRANSFER_MONEY, BTN_TRANSFER_MONEY));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_TRANSFER_MONEY, BTN_TRANSFER_MONEY));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_USE_BALANCE, BTN_USE_BALANCE));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_USE_BALANCE, BTN_USE_BALANCE));

        this.setReplyMarkup(inlineKeyboardMarkup);
    }
}
