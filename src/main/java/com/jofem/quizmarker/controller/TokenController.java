package com.jofem.quizmarker.controller;


import com.jofem.quizmarker.Repository.UserRepository;
import com.jofem.quizmarker.jwtsecurity.JwtGenerator;
import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.model.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@Controller
@RequestMapping(value = "/secure/token")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody JwtUser jwtUser, Model model){
        System.out.println("User detail: " + jwtUser.toString());
        String token =  jwtGenerator.generate(jwtUser);
        model.addAttribute("token", token);
        return "dashboard";
    }
}
