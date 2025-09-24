package com.kanbanflow.kanban_api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final SecretKey key = Jwts.SIG.HS256.key().build();
    private final long expiration = 1000*60*60;

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key)
                .compact();
    }

    // Extract UserName from claims
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    // Extract expirationDate from claims
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    // Extract a particular claim from all claims
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract All Claims
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = userDetails.getUsername();
        return (userName.equals(extractUserName(token)) && !isTokenExpired(token));
    }



}
