package com.example.demo.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

public class JwtTokenProvider {
    private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${jwt.secret}")
    private  String jwtSecret;
    @Value("${jwt.expireIn")
    private int jwtExpirationInMs;

    public String generateAccessToken(Authentication authentication){
        try{
            UserPrincipal userPrincipal = (UserPrincipal)  authentication.getPrincipal();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime()+ jwtExpirationInMs);
            List<String> roles = userPrincipal.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            return Jwts.builder()
                    .setId(userPrincipal.getId()+"")
                    .setSubject(userPrincipal.getId()+"")
                    .claim("user", userPrincipal)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS256,jwtSecret)
                    .compact();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException ex){
            logger.error("Invalid JWT signature");
        }catch(MalformedJwtException ex){
            logger.error("Invalid JWT token", ex);
        }catch (UnsupportedJwtException ex){
            logger.error("Unsupported JWT token "+ex);
        }catch(IllegalArgumentException ex){
            logger.error("JWT claims string is empty"+ex);
        }
        return false;
    }
}
