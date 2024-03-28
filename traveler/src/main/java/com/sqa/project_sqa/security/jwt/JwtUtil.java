package com.sqa.project_sqa.security.jwt;

import com.sqa.project_sqa.entities.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtUtil {
    @Value("${jwt.secretKey}")
    private String JWT_SECRET;

    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION;

    public String extractUsername(String token){
        return extractClamis(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClamis(token, Claims::getExpiration);
    }

    public <T> T extractClamis(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("Name", user.getName());
        claims.put("userId", user.getUserId());
        claims.put("role", user.getRole());
        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256,JWT_SECRET).compact();
    }

    // validate jwt token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException mje) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException eje) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException uje) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException iae) {
            log.error("JWT claims String is empty");
        }
        return false;
    }

}
