package com.example.demo.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil{
    private final String secretKeyValue ="this-is-my-secret-key-make-it-at-least-32-chars!!";
    private final long tokenValidity =24 * 60 * 60 * 1000;
    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretKeyValue.getBytes());
    }
    public String createToken(String userName){
        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(getSigningKey())
                .compact();
    }
    public String getUserNameFromToken(String jwtToken){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String jwtToken){
        try{
            getUserNameFromToken(jwtToken);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}