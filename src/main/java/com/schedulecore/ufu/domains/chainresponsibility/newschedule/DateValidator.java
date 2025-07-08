package com.schedulecore.ufu.domains.chainresponsibility.newschedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.models.NewSchedule;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class DateValidator extends ScheduleValidator {
    @Override
    public boolean check(NewSchedule schedule) {
    if (schedule.getData() == null)
        throw new IllegalArgumentException("Date cannot be null");
    if(schedule.getData().before(Date.from(Instant.now())))
        throw new IllegalArgumentException("Date cannot be in the past, data invalid: " + schedule.getData());

    return checkNext(schedule);
    }
}
