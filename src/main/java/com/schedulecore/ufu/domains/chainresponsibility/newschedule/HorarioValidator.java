package com.schedulecore.ufu.domains.chainresponsibility.newschedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.models.NewSchedule;

public class HorarioValidator extends ScheduleValidator {
    @Override
    public boolean check(NewSchedule schedule) {
    if (schedule.getHorario() == null)
        throw new IllegalArgumentException("Schedule time cannot be null");
    if(schedule.getHorario().toLocalTime().getMinute() != 0 || schedule.getHorario().toLocalTime().getSecond() != 0)
        throw new IllegalArgumentException("Schedule time cannot have minutes or seconds, only hours are allowed");

    return checkNext(schedule);
    }
}
