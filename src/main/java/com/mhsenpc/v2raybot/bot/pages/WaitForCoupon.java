package com.mhsenpc.v2raybot.bot.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WaitForCoupon extends BasePage {

    @Autowired
    public WaitForCoupon() {

        setText("لطفا کوپن را ارسال کنید");
    }
}
