package com.schedulecore.ufu.infrasctructure.database.repositorys;

import com.schedulecore.ufu.infrasctructure.database.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
