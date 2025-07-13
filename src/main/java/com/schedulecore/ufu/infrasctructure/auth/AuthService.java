package com.schedulecore.ufu.infrasctructure.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    @Value("${spring.mail.username}")
    private String email;

    public String generateToken(UserModel model) {
        return Jwts.builder()
                .setSubject(model.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, email)
                .compact();
    }

    public String parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(email)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null; // Token inválido ou expirado
        }
    }

}
