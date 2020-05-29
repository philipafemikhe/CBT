package com.jofem.quizmarker.controller.admin;

import com.jofem.quizmarker.Service.LicenseService;
import com.jofem.quizmarker.Service.UserService;
import com.jofem.quizmarker.model.License;
import com.jofem.quizmarker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ApprovalController {
    @Autowired
    UserService userService;
    @Autowired
    LicenseService licenseService;

    @RequestMapping("/pending/users")
    public List<User> listPendingUsers(){
       return this.userService.findByStatus("pending");
    }

    @RequestMapping("/pending/license")
    public List<License> listPendingLicense(){
        return this.licenseService.getPendingLicenses();
    }

    @RequestMapping("/approve/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String approveUser(@PathVariable Long userId){
       User user= this.userService.findById(userId);
       if(user != null){
           if(user.getStatus().equals("Active")){
               return user.getLastName() + " is already Active";
           }
           user.setStatus("Active");
           this.userService.save(user);
           return user.getLastName() + " status changed to Active";
       }else {
           return "User not found with the specified id";
       }
    }

    @RequestMapping("/revoke/user/{userId}")
    public String revokeUser(@PathVariable Long userId){
        User user= this.userService.findById(userId);
        if(user != null){
            if(user.getStatus().equals("pending")){
                return user.getLastName() + " has been pending";
            }
            user.setStatus("pending");
            this.userService.save(user);
            return user.getLastName() + " status changed to pending";
        }else {
            return "User not found with the specified id";
        }
    }

    @RequestMapping("/approve/license/{licenseId}")
    public String approveLicense(@PathVariable Long licenseId){
        License license= this.licenseService.findById(licenseId);
        if(license != null){
            if(license.getStatus().equals("Active")){
                return license.getSubscriberName() + "'s license is already Active";
            }
            license.setStatus("Active");
            this.licenseService.save(license);
            return license.getSubscription().getPackageName() + " status changed to Active for user " + license.getSubscriberName();
        }else {
            return "License not found with the specified id";
        }
    }

    @RequestMapping("/revoke/license/{licenseId}")
    public String revokeLicense(@PathVariable Long licenseId){
        License license= this.licenseService.findById(licenseId);
        if(license != null){
            if(license.getStatus().equals("pending")){
                return license.getSubscriberName() + "'s license has been pending";
            }
            license.setStatus("pending");
            this.licenseService.save(license);
            return license.getSubscription().getPackageName() + " status changed to pending for user " + license.getSubscriberName();
        }else {
            return "License not found with the specified id";
        }
    }
}
