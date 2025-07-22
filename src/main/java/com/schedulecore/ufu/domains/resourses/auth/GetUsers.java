package com.schedulecore.ufu.domains.resourses.auth;

import com.schedulecore.ufu.domains.models.UserModel;

import java.util.List;

public interface GetUsers {
    List<UserModel> execute();
}
