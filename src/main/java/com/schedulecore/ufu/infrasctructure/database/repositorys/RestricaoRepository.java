package com.schedulecore.ufu.infrasctructure.database.repositorys;

import com.schedulecore.ufu.infrasctructure.database.entitys.GinasioEntity;
import com.schedulecore.ufu.infrasctructure.database.entitys.RestricaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface RestricaoRepository extends JpaRepository<RestricaoEntity, Long> {
    RestricaoEntity findByGinasioAndData(String ginasio, Date data);
}
