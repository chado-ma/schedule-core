package com.schedulecore.ufu.infrasctructure.database.repositorys;

import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepostiory extends JpaRepository<ReservaEntity, Long> {
    ;
}
