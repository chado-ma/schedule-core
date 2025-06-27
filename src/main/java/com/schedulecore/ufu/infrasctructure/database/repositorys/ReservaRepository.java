package com.schedulecore.ufu.infrasctructure.database.repositorys;

import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.MonthDay;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findAllByCampusAndData(String campus, MonthDay data);
    List<ReservaEntity> findAllByData(MonthDay data);
    List<ReservaEntity> findAllByMatriculaAluno(String matricula);
    ReservaEntity findByGinasioAndHorarioAndData(String ginasio, Time horario, MonthDay data);
    void deleteByHorarioAndGinasioAndDataAndMatriculaAluno(Time horario, String ginasio, MonthDay data, String matriculaAluno);
}
