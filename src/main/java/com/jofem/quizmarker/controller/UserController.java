package com.jofem.quizmarker.controller;

import com.jofem.quizmarker.Repository.RoleRepository;
import com.jofem.quizmarker.Service.UserService;
import com.jofem.quizmarker.config.tenantDao.TenantDataAccessObject;
import com.jofem.quizmarker.model.Role;
import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.shared.ActiveTenant;
import com.jofem.quizmarker.shared.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/add")
    public String getSubscriptionForm(Model model){
        User user =  new User();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "subscribe";
    }

    @PostMapping("/")
    public String save(@ModelAttribute("user") User user){
        System.out.println("Saving user: " + user.toString());
        String encodedPsw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPsw);
//        try{
//            userService.save(user);
//        }catch(Exception er){
//            er.printStackTrace();
//        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getUsers(Model model){
        List<User> usersList = new ArrayList<>();
        userService.getUser()
                .forEach(usersList::add);
        model.addAttribute("records", usersList);
        return "user_res";
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id){
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
        return;
    }

    @PostMapping("/login")
    public String login(@RequestBody Credentials credentials){
        System.out.println("Loging you in");
        String encodedPsw = bCryptPasswordEncoder.encode(credentials.getPassword());
        User thisUser = userService.findByUsernameAndPassword(credentials.getUsername(), encodedPsw);
        if(thisUser != null){
            ActiveTenant.currentUser = thisUser;
            ActiveTenant.tenantDatabase = "examiner_" + thisUser.getId();
            return "dashboard";
        }else{
            return "error_page";
        }
    }


}
