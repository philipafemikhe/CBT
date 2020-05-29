package com.jofem.quizmarker.jwtsecurity;


import com.jofem.quizmarker.model.jwt.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser){

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", String.valueOf(jwtUser.getRole()));

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "philip").compact();

    }

    public String generate(String jwtUser) {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser);
        claims.put("userId", null);
        claims.put("role", null);

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "philip").compact();

    }
}
