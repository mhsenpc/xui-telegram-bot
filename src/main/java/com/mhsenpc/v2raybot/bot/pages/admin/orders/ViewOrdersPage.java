package com.mhsenpc.v2raybot.bot.pages.admin.orders;

import com.mhsenpc.v2raybot.bot.pages.BasePage;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

@Component
public class ViewOrdersPage extends BasePage {

    public static final String BTN_ALL_ORDERS = "همه سفارشات";
    public static final String BTN_CONFIRMED_ORDERS = "سفارشات تایید شده";
    public static final String BTN_PENDING_ORDERS = "سفارشات در انتظار تایید";

    public ViewOrdersPage() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_ALL_ORDERS), new KeyboardButton(BTN_CONFIRMED_ORDERS) );
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_PENDING_ORDERS));
        replyKeyboardMarkup.addRow(new KeyboardButton(BasePage.BTN_BACK));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
