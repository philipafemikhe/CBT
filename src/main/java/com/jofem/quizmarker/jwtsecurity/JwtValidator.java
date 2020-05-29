package com.jofem.quizmarker.jwtsecurity;


import com.jofem.quizmarker.model.jwt.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "philip";

    public JwtUser validate(String token) {
        JwtUser jwtUser = null;
                Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        try {
            jwtUser =  new JwtUser();
            System.out.println("Subject: " + body.getSubject() + " id " + body.get("userId") + " role " + body.get("role"));
            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String)body.get("userId")) );
            jwtUser.setRole((String) body.get("role"));
            System.out.println("User " + jwtUser.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return jwtUser;

    }
}
