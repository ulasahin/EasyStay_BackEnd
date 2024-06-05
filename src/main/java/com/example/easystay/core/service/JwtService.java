package com.example.easystay.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private final long EXPIRATION = 600_000;
    private final String SECRET_KEY = "34d76665c2ad8306fdad5a91f0a936aed4284ecf35faf55e61a08f371d8b8c40a649f1e94f6367f03345e02d37ce03c98c4a66ac27eb3535ffaad53cb7d7a3b3";
    public String generateToken(String userName, Map<String, Object> extraClaims){
        String token = Jwts
                .builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date (System.currentTimeMillis() + EXPIRATION))
                .claims(extraClaims)
                .subject(userName)
                .signWith(getSignKey())
                .compact();

        return token;
    }
    public boolean validateToken(String token){

        Date expirationDate = getClaimsFromToken(token).getExpiration();

        return expirationDate.after(new Date());
    }

    public String extractUserName(String token){
        return getClaimsFromToken(token).getSubject();
    }
    private Claims getClaimsFromToken(String token){
        SecretKey key = (SecretKey) getSignKey();
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
