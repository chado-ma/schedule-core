package com.schedulecore.ufu.domains.chainresponsibility.newschedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.models.NewSchedule;

import java.sql.Date;
import java.sql.Time;
import java.time.*;

public class DateValidator extends ScheduleValidator {
    @Override
    public boolean check(NewSchedule schedule) {
    if (schedule.getData() == null)
        throw new IllegalArgumentException("Date cannot be null");
    if( isAgendamentoNoPassado(schedule.getData(), schedule.getHorario()))
        throw new IllegalArgumentException("Date cannot be in the past, data invalid: " + schedule.getData());

    return checkNext(schedule);
    }

    private boolean isAgendamentoNoPassado(Date data, Time horario) {
        LocalDate localDate = data.toLocalDate();
        LocalTime localTime = horario.toLocalTime();
        return  LocalDateTime.of(localDate, localTime).isBefore(LocalDateTime.now());
    }
}
