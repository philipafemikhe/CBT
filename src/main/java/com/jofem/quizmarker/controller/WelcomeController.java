package com.jofem.quizmarker.controller;

import com.jofem.quizmarker.Repository.UserRepository;
import com.jofem.quizmarker.config.tenantDao.TenantDataAccessObject;
import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.shared.ActiveTenant;
import com.jofem.quizmarker.shared.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String welcome(Model model){
        if(ActiveTenant.currentUser != null){
            model.addAttribute("username", ActiveTenant.currentUser.getEmail());
            model.addAttribute("db", ActiveTenant.tenantDatabase);
        }

        return "index";
    }

    @GetMapping("/generate/token")
    public String getTokenForm(){
        return "get_token";
    }

    @GetMapping("/all/users")
    public String listUsers(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "user_res";

    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        model.addAttribute("credentials", new Credentials());
        return "quiz_login";
    }

    @GetMapping("/logout")
    public String logUserOut(){
        System.out.println("Loging you out...");
        ActiveTenant.tenantDatabase = null;
        ActiveTenant.currentUser = null;
        JdbcTemplate jdbcTemplate = TenantDataAccessObject.logoutToMaster();
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logYouOut(){
        System.out.println("Loging you out...");
        ActiveTenant.tenantDatabase = null;
        ActiveTenant.currentUser = null;
        JdbcTemplate jdbcTemplate = TenantDataAccessObject.logoutToMaster();
        return "redirect:/";
    }
}
