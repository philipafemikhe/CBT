package com.jofem.quizmarker.Service;

import com.jofem.quizmarker.Repository.UserRepository;
import com.jofem.quizmarker.config.tenantDao.TenantDataAccessObject;
import com.jofem.quizmarker.model.User;
import com.jofem.quizmarker.shared.ActiveTenant;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TenantDataAccessObject tenantDataAccessObject;


    //save an objects
    @Transactional
    public User save(User user){
        System.out.println("UserService: " + user.toString());
        User theUser = userRepository.save(user);
        System.out.println("Got here.. user saved " + theUser.toString());
        ActiveTenant.currentUser = theUser;
        ActiveTenant.tenantDatabase = "examiner_" + theUser.getId();
        this.setupDB(theUser);
        return theUser;
    }

    //get an objects
    @Transactional
    public User getUser(Long id){
        return userRepository.findById(id).get();
    }

    //get all objects

    @Transactional
    public List<User> getUser(){
        List<User> usersList =  new ArrayList<>();
        userRepository.findAll()
                .forEach(usersList::add);
        return usersList;
    }

    //update objects

    @Transactional
    public User update(User users, Long id){
        User theUser = userRepository.findById(id).get();
        if(theUser != null){
            theUser.setEmail(users.getEmail());
            theUser.setFirstName(users.getFirstName());
            theUser.setLastName(users.getLastName());
            theUser.setPassword(users.getPassword());
            //theUser.setPassword(BCrypt.hashpw(users.getPassword(), BCrypt.gensalt));
            theUser.setPhone(users.getPhone());
            theUser.setStatus(users.getStatus());
            theUser.setUserType(users.getUserType());
            return userRepository.save(theUser);
        }
        return users;
    }

    @Transactional
    public void delete(Long id){
       userRepository.deleteById(id);
       return;
    }

    public List<User> findByStatus(String pending) {
       return this.userRepository.findByStatus(pending);
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).get();
    }

    //delete objects

    public void setupDB(User user){
        System.out.println("Seting up DB");
        String dbName = "JDBCtenant_" +  user.getId();
        tenantDataAccessObject.setDataSource(ActiveTenant.currentUser);
    }

    public User findByUsernameAndPassword(String username, String encodedPsw) {
//        return userRepository.findByEmailAndPassword(username, encodedPsw);
        return userRepository.findByEmail(username);
    }
}
