package com.schedulecore.ufu.infrasctructure.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthAdapter implements AuthPort {
    private final AuthService authService;
    @Override
    public String generateAuthToken(UserModel user) {
        log.info("Generating auth token for user: {}", user.getEmail());
        return authService.generateToken(user);
    }

    @Override
    public UserModel authenticateUser(String token) {
        log.info("Authenticating user with token startig with: {}", token.substring(0, 5));
        return authService.parseToken(token);
    }
}
