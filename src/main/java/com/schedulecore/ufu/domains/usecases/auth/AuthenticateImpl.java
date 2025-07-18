package com.schedulecore.ufu.domains.usecases.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import com.schedulecore.ufu.domains.resourses.auth.Authenticate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateImpl implements Authenticate {
    private final AuthPort authPort;

    @Override
    public UserModel execute(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        UserModel user = authPort.authenticateUser(token);
        if (user == null) throw new AuthenticationCredentialsNotFoundException("Invalid or Expired token");
        return user;
    }

}
