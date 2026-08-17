package com.employee.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expiration=1000*60*60; //1 hrs

    public JwtService(@Value("${jwt.secret}") String secret){
        this.secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long userId,String email,String role){
       return Jwts.builder()
               .subject(email)
               .claim("userId",userId)
               .claim("role",role)
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis()+expiration))
               .signWith(secretKey)
               .compact();
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims=extractAllClaims(token);
            return  claims.getExpiration().after(new Date());
        }
        catch (Exception e){
            return  false;
        }
    }
}
