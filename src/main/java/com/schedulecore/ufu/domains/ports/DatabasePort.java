package com.schedulecore.ufu.domains.ports;

import com.schedulecore.ufu.domains.models.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface DatabasePort {
    List<ScheduleModel> findSchedulesByGinasioAndMonthAndDay(Date data, String campus);
    List<ScheduleModel> findSchedulesByMonthAndDay(Date data);
    void saveSchedule(NewSchedule model);
    void saveGinasio(GinasioModel model);
    void updateSchedule(NewSchedule model);
    void updateGinasio(GinasioModel model);
    void deleteSchedule(Time horario, Date data, String ginasio, String matriculaAluno);
    Optional<GinasioModel> findGinasioById(String id);
    Optional<ScheduleModel> findScheduleByHorarioAndMounthDayAndGinasio(Time horario, Date monthDay, String ginasio);
    void deleteGinasio(String ginasio);
    Optional<RestricaoModel> findRestricaoByGinasioAndData(String ginasio, Date data);
    void saveRestricao(RestricaoModel model);
    void deleteRestricao(RestricaoModel model);
    List<GinasioModel> findAllGinasios();
    List<RestricaoModel> findAllRestricoes();
    List<ScheduleModel> findUserSchedules(String matricula);
    List<ScheduleModel> findAllSchedules();
    Optional<UserModel> findUserByEmail(String email);
    void saveUserOrUpdateUser(UserModel user);

    void saveUserOrUpdateAdm(UserModel user);
}
