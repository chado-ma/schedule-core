package com.schedulecore.ufu.infrasctructure.database;

import com.schedulecore.ufu.domains.models.*;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.infrasctructure.database.entitys.GinasioEntity;
import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import com.schedulecore.ufu.infrasctructure.database.entitys.RestricaoEntity;
import com.schedulecore.ufu.infrasctructure.database.entitys.UserEntity;
import com.schedulecore.ufu.infrasctructure.database.repositorys.GinasioRepository;
import com.schedulecore.ufu.infrasctructure.database.repositorys.ReservaRepository;
import com.schedulecore.ufu.infrasctructure.database.repositorys.RestricaoRepository;
import com.schedulecore.ufu.infrasctructure.database.repositorys.UserRepository;
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
    private final UserRepository userRepository;
    private final RestricaoRepository restricaoRepository;

    @Override
    public List<ScheduleModel> findSchedulesByGinasioAndMonthAndDay(Date data, String ginasio) {
        log.info("getSchedulesByCampusAndMonthAndDay schedules for ginasio: {}, data: {}", ginasio, data);
        return reservaRepository.findAllByGinasioAndData(ginasio, data)
                .stream()
                .map(ReservaEntity::toModel)
                .toList();
    }

    @Override
    public List<ScheduleModel> findSchedulesByMonthAndDay(Date data) {
        log.info("findSchedulesByMonthAndDay schedules for data: {}", data);
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
                model.getQuantidadePessoas(),
                model.getEmail()
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
    public Optional<GinasioModel> findGinasioById(String id) {
        log.info("getGinasioById - ginasio by id: {}", id);
        return ginasioRepository.findById(id).flatMap(ginasioEntity -> Optional.ofNullable(ginasioEntity.toModel()));
    }

    @Override
    public Optional<ScheduleModel> findScheduleByHorarioAndMounthDayAndGinasio(Time horario, Date data, String ginasio) {
        log.info("findScheduleByHorarioAndMounthDayAndGinasio - searching schedule for ginasio: {}, horario: {}, data: {}", ginasio, horario, data);
        return Optional.ofNullable(reservaRepository.findByGinasioAndHorarioAndData(ginasio, horario, data))
                .map(ReservaEntity::toModel);
    }

    @Override
    @Transactional
    public void deleteGinasio(String ginasio) {
        log.info("Deleting ginasio: {}" , ginasio);
       ginasioRepository.deleteById(ginasio);
    }

    @Override
    public Optional<RestricaoModel> findRestricaoByGinasioAndData(String ginasio, Date data) {
        log.info("findRestricaoByGinasioAndData - searching restriction for ginasio: {}, data: {}", ginasio, data);
        return Optional.ofNullable(restricaoRepository.findByGinasioAndData(ginasio, data))
                .map(RestricaoEntity::toModel);
    }

    @Override
    public void saveRestricao(RestricaoModel model) {
        log.info("Saving new restriction: {}", model);
         Optional.ofNullable(restricaoRepository.findByGinasioAndData(
                         model.getGinasio(),
                         model.getData()
                 )).ifPresentOrElse(
                    existingRestricao -> {
                        throw new IllegalArgumentException("Restriction already exists for ginasio: " + existingRestricao.getGinasio() + " and data: " + existingRestricao.getData());
                    },
                    () -> restricaoRepository.save(new RestricaoEntity(
                            model.getGinasio(),
                            model.getData(),
                            model.getDescricao()
                    ))
                );
    }

    @Override
    public void deleteRestricao(RestricaoModel model) {
        Optional.ofNullable(restricaoRepository.findByGinasioAndData(model.getGinasio()
                , model.getData())).map(
                restricaoEntity -> {
                    log.info("Delete restriction: {}", model);
                    restricaoRepository.delete(restricaoEntity);
                    return restricaoEntity;
                }
        ).orElseThrow(() -> new IllegalArgumentException("Restriction not found for ginasio: " + model.getGinasio() + " and data: " + model.getData()));
    }

    @Override
    public List<GinasioModel> findAllGinasios() {
        log.info("findAllGinasios - fetching all ginasios");
        return ginasioRepository.findAll()
                .stream()
                .map(GinasioEntity::toModel)
                .toList();
    }

    @Override
    public List<RestricaoModel> findAllRestricoes() {
        log.info("findAllRestricoes - fetching all restrictions");
        return restricaoRepository.findAll()
                .stream()
                .map(RestricaoEntity::toModel)
                .toList();
    }

    @Override
    public List<ScheduleModel> findUserSchedules(String matricula) {
        log.info("findUserSchedules - getting schedules for user with matricula: {}", matricula);
        return reservaRepository.findByMatriculaAluno(matricula)
                .stream()
                .map(ReservaEntity::toModel)
                .toList();
    }

    @Override
    public List<ScheduleModel> findAllSchedules() {
        return reservaRepository.findAll()
                .stream()
                .map(ReservaEntity::toModel)
                .toList();

    }

    @Override
    public Optional<UserModel> findUserByEmail(String email) {
        log.info("findUserByEmail - searching user by email: {}", email);
        return userRepository.findById(email)
                .map(UserEntity::toModel);
    }

    @Override
    public void saveUserOrUpdateUser(UserModel user) {
        log.info("saveUserOrUpdateUser user: {}", user);
        UserEntity userEntity = new UserEntity();
        userEntity.setMatricula(user.getMatricula());
        userEntity.setNome(user.getNome());
        userEntity.setEmail(user.getEmail());
        userEntity.setTelefone(user.getTelefone());
        userEntity.setRole(user.getAcess().name());
        userRepository.findById(userEntity.getEmail()).ifPresentOrElse(
                userEntity1 -> {
                    userEntity1.setMatricula(user.getMatricula());
                    userEntity1.setNome(user.getNome());
                    userEntity1.setTelefone(user.getTelefone());
                    userRepository.deleteById(user.getEmail());
                    userRepository.save(userEntity1);
                }, () -> userRepository.save(userEntity)
        );
    }

    @Override
    public List<UserModel> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toModel)
                .toList();
    }

    @Override
    public void saveUserOrUpdateAdm(UserModel user) {
        log.info("saveUserOrUpdateAdm user: {}", user);
        UserEntity userEntity = new UserEntity();
        userEntity.setMatricula(user.getMatricula());
        userEntity.setNome(user.getNome());
        userEntity.setEmail(user.getEmail());
        userEntity.setTelefone(user.getTelefone());
        userEntity.setRole(user.getAcess().name());
        userRepository.findById(userEntity.getEmail()).ifPresentOrElse(
                userEntity1 -> {
                    userEntity1.setMatricula(user.getMatricula());
                    userEntity1.setNome(user.getNome());
                    userEntity1.setTelefone(user.getTelefone());
                    userEntity1.setRole(user.getAcess().name());
                    userRepository.deleteById(user.getEmail());
                    userRepository.save(userEntity1);
                }, () -> userRepository.save(userEntity)
        );
    }

}
