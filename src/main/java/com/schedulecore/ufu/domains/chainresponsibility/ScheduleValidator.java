package com.schedulecore.ufu.domains.chainresponsibility;

import com.schedulecore.ufu.domains.models.NewSchedule;

public abstract class ScheduleValidator {
    private ScheduleValidator nextValidator;

    public static ScheduleValidator link(ScheduleValidator first, ScheduleValidator... chain) {
        ScheduleValidator head = first;
        for (ScheduleValidator nextInChain: chain) {
            head.nextValidator = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(NewSchedule schedule);

    protected boolean checkNext(NewSchedule schedule) {
        if (nextValidator == null) {
            return true;
        }
        return nextValidator.check(schedule);
    }
}
