package com.schedulecore.ufu.domains.resourses.auth;

import com.schedulecore.ufu.domains.models.UserModel;

public interface GenerateAuth {
    String execute(UserModel user);
}
