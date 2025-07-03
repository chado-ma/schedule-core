package com.schedulecore.ufu.infrasctructure.database;

import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.infrasctructure.database.entitys.GinasioEntity;
import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import com.schedulecore.ufu.infrasctructure.database.repositorys.GinasioRepository;
import com.schedulecore.ufu.infrasctructure.database.repositorys.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseAdapter implements DatabasePort {
    private final ReservaRepository reservaRepository;
    private final GinasioRepository ginasioRepository;

    @Override
    public List<ScheduleModel> getSchedulesByCampusAndMonthAndDay(Date data, String campus) {
        log.info("getSchedulesByCampusAndMonthAndDay schedules for campus: {}, data: {}", campus, data);
        return reservaRepository.findAllByCampusAndData(campus, data)
                .stream()
                .map(ReservaEntity::toModel)
                .toList();
    }

    @Override
    public List<ScheduleModel> getSchedulesByMonthAndDay(Date data) {
        log.info("getSchedulesByMonthAndDay schedules for data: {}", data);
        return reservaRepository.findAllByData(data)
                .stream()
                .map(ReservaEntity::toModel)
                .toList();
    }

    @Override
    public void saveSchedule(NewSchedule model) {
        log.info("Saving new schedule: {}", model);
        reservaRepository.save(new ReservaEntity(
                model.getHorario(),
                model.getData(),
                model.getGinasio(),
                model.getResponsavel(),
                model.getCurso(),
                model.getCampus().name(),
                model.getMatriculaAluno(),
                model.getTelefone(),
                model.getQuantidadePessoas()
        ));
    }

    @Override
    public void saveGinasio(GinasioModel model) {
        log.info("Saving new ginasio: {}", model);
        GinasioEntity ginasioEntity = new GinasioEntity();
        ginasioEntity.setNome(model.getNome());
        ginasioEntity.setStartTime(model.getStartTime());
        ginasioEntity.setEndTime(model.getEndTime());
        ginasioEntity.setCampus(model.getCampus());
        ginasioRepository.save(ginasioEntity);

    }

    @Override
    @Transactional
    public void updateSchedule(NewSchedule model) {
        log.info("Updating schedule: {}", model);
        Optional.ofNullable(
                reservaRepository.findByGinasioAndHorarioAndData(model.getGinasio(), model.getHorario(), model.getData())
        ).ifPresent(reserva -> {
            reserva.setResponsavel(model.getResponsavel());
            reserva.setCurso(model.getCurso());
            reserva.setMatriculaAluno(model.getMatriculaAluno());
            reserva.setTelefone(model.getTelefone());
            reserva.setQuantidadePessoas(model.getQuantidadePessoas());
            reservaRepository.save(reserva);
        });
    }

    @Override
    @Transactional
    public void updateGinasio(GinasioModel model) {
        log.info("Updating ginasio: {}", model);
        ginasioRepository.findById(model.getNome())
                .ifPresent(ginasioEntity -> {
                            ginasioEntity.setStartTime(model.getStartTime());
                            ginasioEntity.setEndTime(model.getEndTime());
                            ginasioEntity.setNome(model.getNome());
                            ginasioEntity.setCampus(model.getCampus());
                            ginasioRepository.save(ginasioEntity);
                        }
                );

    }

    @Override
    @Transactional
    public void deleteSchedule(Time horario, Date data, String ginasio, String matricula) {
        log.info("Deleting schedule for horario: {}, data: {}, ginasio: {}, matricula: {}", horario, data, ginasio, matricula);
        reservaRepository.deleteByHorarioAndGinasioAndDataAndMatriculaAluno(horario, ginasio, data, matricula);
    }

    @Override
    public Optional<GinasioModel> getGinasioById(String id) {
        log.info("getGinasioById - ginasio by id: {}", id);
        return ginasioRepository.findById(id).flatMap(GinasioEntity::toModel);
    }

    @Override
    public Optional<ScheduleModel> findScheduleByHorarioAndMounthDayAndGinasio(Time horario, Date data, String ginasio) {
        log.info("findScheduleByHorarioAndMounthDayAndGinasio - searching schedule for ginasio: {}, horario: {}, data: {}", ginasio, horario, data);
        return Optional.ofNullable(reservaRepository.findByGinasioAndHorarioAndData(ginasio, horario, data))
                .map(ReservaEntity::toModel);
    }
}
