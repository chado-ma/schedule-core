package com.schedulecore.ufu.domains.usecases.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.auth.GenerateAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateAuthImpl implements GenerateAuth {
    private final DatabasePort databasePort;
    private final AuthPort authPort;

    @Override
    public String execute(UserModel user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User and email cannot be null or empty");
        }
        databasePort.saveUserOrUpdateUser(user);
        return authPort.generateAuthToken(user);
    }
}
