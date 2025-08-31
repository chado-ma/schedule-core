package com.schedulecore.ufu.domains.usecases.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.auth.GenerateAuth;
import com.schedulecore.ufu.domains.resourses.auth.GerenateAdm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateAdmImpl implements GerenateAdm {
    private final DatabasePort databasePort;

    @Override
    public void execute(UserModel user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User and email cannot be null or empty");
        }
        databasePort.saveUserOrUpdateAdm(user);
    }
}
