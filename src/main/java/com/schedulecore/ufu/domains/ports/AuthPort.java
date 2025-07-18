package com.schedulecore.ufu.domains.ports;

import com.schedulecore.ufu.domains.models.UserModel;

public interface AuthPort {
    String generateAuthToken(UserModel user);

    UserModel authenticateUser(String token);
}
