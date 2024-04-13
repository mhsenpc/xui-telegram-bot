package com.mhsenpc.v2raybot.bot.pages;

import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;

public class BuyAccountSelectPaymentMethodPage extends BasePage {

    public static final String BTN_TRANSFER_MONEY = "کارت به کارت";
    public static final String BTN_USE_BALANCE = "پرداخت با کیف پول";

    public BuyAccountSelectPaymentMethodPage() {
        setText("مبلغ را چگونه پرداخت می کنید؟");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_TRANSFER_MONEY, PaymentMethod.TRANSFER_MONEY.toString()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton(BTN_USE_BALANCE, PaymentMethod.WALLET.toString()));

        this.setReplyMarkup(inlineKeyboardMarkup);
    }
}
