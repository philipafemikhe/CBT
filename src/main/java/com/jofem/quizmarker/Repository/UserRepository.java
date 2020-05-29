package com.jofem.quizmarker.Repository;

import com.jofem.quizmarker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String s);

    List<User> findByStatus(String pending);

    User findByEmailAndPassword(String email, String encodedPsw);

    //User findByEmail(String s);
}
