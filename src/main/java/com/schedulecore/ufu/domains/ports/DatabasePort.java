package com.schedulecore.ufu.domains.ports;

import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.infrasctructure.api.v1.ScheduleController;

import java.sql.Date;
import java.sql.Time;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

public interface DatabasePort {
    List<ScheduleModel> getSchedulesByGinasioAndMonthAndDay(Date data, String campus);
    List<ScheduleModel> getSchedulesByMonthAndDay(Date data);
    void saveSchedule(NewSchedule model);
    void saveGinasio(GinasioModel model);
    void updateSchedule(NewSchedule model);
    void updateGinasio(GinasioModel model);
    void deleteSchedule(Time horario, Date data, String ginasio, String matriculaAluno);
    Optional<GinasioModel> getGinasioById(String id);
    Optional<ScheduleModel> findScheduleByHorarioAndMounthDayAndGinasio(Time horario, Date monthDay, String ginasio);
}
