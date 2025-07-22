package com.schedulecore.ufu.domains.usecases.auth;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.auth.GetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUsersImpl implements GetUsers {
    private final DatabasePort databasePort;
    @Override
    public List<UserModel> execute() {
        return databasePort.findAllUsers();
    }
}
