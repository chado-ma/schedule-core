package com.schedulecore.ufu.infrasctructure.database.repositorys;

import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findAllByGinasioAndData(String ginasio, Date data);

    List<ReservaEntity> findAllByData(Date data);

    List<ReservaEntity> findByMatriculaAluno(String matricula);

    ReservaEntity findByGinasioAndHorarioAndData(String ginasio, Time horario, Date data);

    void deleteByHorarioAndGinasioAndDataAndMatriculaAluno(Time horario, String ginasio, Date data, String matriculaAluno);
}
