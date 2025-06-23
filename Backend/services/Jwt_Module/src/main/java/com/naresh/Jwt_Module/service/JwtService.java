package com.naresh.Jwt_Module.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
//    @Value("${jwt.secret}")
//    private String secret;
private String secret="YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYQ==";

    private SecretKey key;
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    public String generateToken(String username, List<String> roles){
        Map<String,Object> claims=new HashMap<>();
        claims.put("roles",roles);
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuer("naresh-kushwaha")
                .audience().add("jaggery-frontend")
                .and()
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .and()
                .signWith(getKey())
                .compact();


    }
    public String generateRefreshToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuer("naresh-kushwaha")
                .audience().add("jaggery-frontend")
                .and()
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .signWith(getKey())
                .compact();
    }
    public Map<String ,Object>validateAndExtractClaims(String token){
        Map<String ,Object>response=new HashMap<>();
        try{
            Claims claims=extractAllClaims(token);
            response.put("username",claims.getSubject());
            response.put("roles",claims.get("roles"));
            response.put("token_id",claims.getId());
            response.put("issuer",claims.getIssuer());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Token validation failed");

        }
        return response;
    }
    public boolean validateToken(String token){
        return isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public Claims extractAllClaims(String token){
        try{
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
