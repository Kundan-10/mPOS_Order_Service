package com.mpos.security;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
	
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.access-token.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${jwt.refresh-token.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public String generateToken(String username, boolean isAccessToken) {

        long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                .build().parseSignedClaims(token)
                .getBody().getSubject();
    }


    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }

}
