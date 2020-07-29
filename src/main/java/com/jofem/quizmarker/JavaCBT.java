package com.jofem.quizmarker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
@EnableWebMvc
@EnableWebSecurity
public class JavaCBT {

	public static void main(String[] args) {
		SpringApplication.run(JavaCBT.class, args);
	}

}
