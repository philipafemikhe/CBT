package com.jofem.quizmarker.controller;

import com.jofem.quizmarker.Repository.SubscriptionRepository;
import com.jofem.quizmarker.Repository.UserRepository;
import com.jofem.quizmarker.Service.LicenseService;
import com.jofem.quizmarker.Service.UserService;
import com.jofem.quizmarker.model.License;
import com.jofem.quizmarker.model.Subscription;
import com.jofem.quizmarker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/secure/license")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @PostMapping("/{userId}/{subscriptionId}")
    public License store(@RequestBody License license, @PathVariable Long userId, @PathVariable Long subscriptionId) throws SQLException {
        User usr = this.userRepository.findById(userId).get();
        license.setUser(usr);
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        license.setSubscription(subscription);
        return licenseService.save(license);
    }

//    @PreAuthorize("hasAnyRole('Admin')")
    @GetMapping("/")
    public List<License> getAllSubscriber(){
        return licenseService.getAllSubscribers();
    }

    @GetMapping("/{id}")
    public License getSubscriber(@PathVariable Long id){
        return licenseService.getSubscriber(id);
    }

    @GetMapping("/{id}/users")
    public License getSubscriberByUserId(@PathVariable Long id){
        return licenseService.getSubscriberByUserId(id);
    }

    @PostMapping("/{id}/users")
    public License saveSubscriberByUserId(@RequestBody License license, @PathVariable Long id){
        license.setUser(license.getUser());
        return licenseService.save(license);
    }


    @PutMapping("/{id}")
    public License update(@RequestBody License license, @PathVariable Long id){
       return licenseService.update(license, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        licenseService.delete(id);
        return;
    }

}
