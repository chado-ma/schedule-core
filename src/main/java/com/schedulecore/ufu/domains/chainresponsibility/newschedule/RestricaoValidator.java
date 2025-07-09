package com.schedulecore.ufu.domains.chainresponsibility.newschedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestricaoValidator extends ScheduleValidator {
    private final DatabasePort databasePort;

    @Override
    public boolean check(NewSchedule schedule) {
        databasePort.findRestricaoByGinasioAndData(
                schedule.getGinasio(),
                schedule.getData()).ifPresent(
                restricao -> {
                    throw new IllegalArgumentException("Schedule cannot be created due to a restriction: " + restricao);
                }
        );
        return checkNext(schedule);
    }
}
