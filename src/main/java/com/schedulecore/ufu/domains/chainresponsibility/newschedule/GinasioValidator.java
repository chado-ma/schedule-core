package com.schedulecore.ufu.domains.chainresponsibility.newschedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GinasioValidator extends ScheduleValidator {
    private final DatabasePort databasePort;

    @Override
    public boolean check(NewSchedule schedule) {
        GinasioModel ginasioModel = databasePort.getGinasioById(schedule.getGinasio()).orElseThrow(
                () -> new IllegalArgumentException("Ginasio not found with: " + schedule.getGinasio())
        );
        if (schedule.getHorario().toLocalTime().isBefore(ginasioModel.getStartTime().toLocalTime()) ||
            schedule.getHorario().toLocalTime().isAfter(ginasioModel.getEndTime().toLocalTime())) {
            throw new IllegalArgumentException("Schedule time is out of Ginasio operating hours: " + ginasioModel);
        }
        return checkNext(schedule);
    }
}
