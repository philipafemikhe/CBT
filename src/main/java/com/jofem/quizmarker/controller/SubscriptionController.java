package com.jofem.quizmarker.controller;

import com.jofem.quizmarker.Service.SubscriptionService;
import com.jofem.quizmarker.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/secure/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/")
    public Subscription save(@RequestBody Subscription subscription){
        return subscriptionService.save(subscription);
    }


    @GetMapping("/list")
    public List<Subscription> getSubscription(){
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionService.getSubscription()
                .forEach(subscriptionList::add);
        return subscriptionList;
    }

    @GetMapping("/{id}")
    public Subscription getSubscription(@PathVariable Long id){
        return subscriptionService.getSubscription(id);
    }

    @PutMapping("/{id}")
    public Subscription update(@RequestBody Subscription subscription, @PathVariable Long id){
        return subscriptionService.update(subscription, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        subscriptionService.delete(id);
        return;
    }

}
