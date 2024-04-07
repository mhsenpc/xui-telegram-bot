package com.mhsenpc.v2raybot.bot.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WaitForReceipt extends BasePage {

    @Autowired
    public WaitForReceipt() {

        setText("لطفا فیش واریزی را ارسال کنید");
    }
}
