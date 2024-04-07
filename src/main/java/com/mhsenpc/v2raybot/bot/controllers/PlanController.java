package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    PlanRepository planRepository;

    @GetMapping()
    public String index(Model model){

        List<Plan> plans = planRepository.findAll();
        model.addAttribute("plans", plans);
        return "plans/list";
    }

    @GetMapping("/add")
    public String addPlan(Model model){

        model.addAttribute("plan", new Plan());
        return "plans/plan-form";
    }

    @GetMapping("/{id}")
    public String addPlan(@PathVariable("id") int id, Model model){

        Plan plan = planRepository.findById(id).get();
        model.addAttribute("plan", plan);
        return "plans/plan-form";
    }

    @PostMapping("/save")
    public String savePlan(@ModelAttribute(name = "plan") Plan plan){

        planRepository.save(plan);
        return "redirect:/plans";
    }

    @GetMapping("{id}/delete")
    public String deletePlan(@PathVariable("id") int id){
        planRepository.deleteById(id);

        return "redirect:/plans";
    }

}
