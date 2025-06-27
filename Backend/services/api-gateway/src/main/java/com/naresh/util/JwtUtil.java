package com.naresh.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String secret="YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYQ==";
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public boolean validateToken(String token){
        return isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
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
