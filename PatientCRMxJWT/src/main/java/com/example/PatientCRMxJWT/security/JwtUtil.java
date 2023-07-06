package com.example.PatientCRMxJWT.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ankit Khatri
 */

@Slf4j
@Component
public class JwtUtil {
    private static final long JWT_TOKEN_EXPIRY = 3600 * 10 * 1000;
    private static final String JWT_SECRET = "ANKIT-JWT-SECRET-KEY-267783#687822432";

    public String getUsername(String token) {
        return getTokenClaim(token, Claims::getSubject);
    }

    public Date getJwtExpirationDate(String token) {
        return getTokenClaim(token, Claims::getExpiration);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername()) && !isExpired(token);
    }

    public String generateJwtToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails.getUsername());
    }

    public <T> T getTokenClaim(String token, Function<Claims, T> claimResolver) {
        return claimResolver.apply(getAllClaimsFromToken(token));
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRY))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    private boolean isExpired(String token) {
        return getJwtExpirationDate(token).before(new Date());
    }

}