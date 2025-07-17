package com.schedulecore.ufu.infrasctructure.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapter implements AuthPort {
    private final AuthService authService;
    @Override
    public String generateAuthToken(UserModel user) {
        return authService.generateToken(user);
    }

    @Override
    public UserModel authenticateUser(String token) {
        return authService.parseToken(token);
    }
}
