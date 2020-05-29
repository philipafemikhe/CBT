package com.jofem.quizmarker.Service.security;

import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.Repository.UserRepository;
import com.jofem.quizmarker.Service.security.CustomUserDetail;
import com.jofem.quizmarker.Repository.UserRepository;
import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.shared.ActiveTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        CustomUserDetail userDetail = null;
        if(user != null){
            userDetail = new CustomUserDetail();
            userDetail.setUser(user);
            ActiveTenant.currentUser = user;
            ActiveTenant.tenantDatabase = "examiner_" + user.getId();
            System.out.println("Authenticated user..." +  user.toString());

        }else{
            throw new UsernameNotFoundException("User not found with email: " + s);
        }
        return userDetail;
    }
}
