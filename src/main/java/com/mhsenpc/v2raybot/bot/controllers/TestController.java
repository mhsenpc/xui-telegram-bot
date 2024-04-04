package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectPlan;
import com.mhsenpc.v2raybot.telegram.interfaces.IRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public IRequest showTest(){

        BuyAccountSelectPlan buyAccountSelectPlanPage = new BuyAccountSelectPlan(11);
        return buyAccountSelectPlanPage;
    }
}
