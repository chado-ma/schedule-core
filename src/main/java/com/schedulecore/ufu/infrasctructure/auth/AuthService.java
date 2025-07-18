package com.schedulecore.ufu.infrasctructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.models.enums.AcessEnum;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

;

@Service
public class AuthService {
    @Value("${spring.mail.username}")
    private String email;
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 horas (1 dia)

    public String generateToken(UserModel model) {
        return JWT.create()
                .withSubject(model.getEmail())
                .withClaim("matricula", model.getMatricula())
                .withClaim("email", model.getEmail())
                .withClaim("name", model.getNome())
                .withClaim("role", model.getAcess().name())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(hashEmail(email)));
    }

    public UserModel parseToken(String token){
        try {
            DecodedJWT decoded = getVerifier().verify(token);
            return UserModel.builder()
                    .email(decoded.getClaim("email").asString())
                    .matricula(decoded.getClaim("matricula").asString())
                    .nome(decoded.getClaim("name").asString())
                    .acess(AcessEnum.valueOf(decoded.getClaim("role").asString()))
                    .build();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String hashEmail(String email) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(email.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash do e-mail", e);
        }
    }

    private JWTVerifier getVerifier() {
        Algorithm algorithm = Algorithm.HMAC256(hashEmail(email));
        return JWT.require(algorithm).build();
    }

}
