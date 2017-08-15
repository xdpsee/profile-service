package com.jerry.demo.usercenter.security.jwt;

import com.jerry.demo.usercenter.api.enums.AuthType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserTokenUtils {

    private static final String CLAIM_KEY_PRINCIPAL = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String secret = "PT!nKg4ySo7C4waA&EM%5XJC%qZkmw3I";
    private static final Long expiration = 10000000L;

    public static String generateToken(Principal principal) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_PRINCIPAL, principal.toString());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }

        return refreshedToken;
    }

    public static Principal getPrincipal(String token) {
        Principal principal = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            String subject = claims.getSubject();
            String components[] = subject.split(":");
            principal = new Principal(AuthType.valueOf(components[0]), components[1]);
        } catch (Exception e) {

        }

        return principal;
    }

    public static Date getExpiresDate(String token) {
        Date expiration;

        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }

        return expiration;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(genExpireDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private static Date genExpireDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}


