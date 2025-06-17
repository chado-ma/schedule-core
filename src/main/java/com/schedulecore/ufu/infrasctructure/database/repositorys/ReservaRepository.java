package com.schedulecore.ufu.infrasctructure.database.repositorys;

import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.MonthDay;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findAllByCampusAndData(String campus, MonthDay data);

    List<ReservaEntity> findAllByData(MonthDay data);

    List<ReservaEntity> findAllByMatriculaAluno(String matricula);
}
