package com.schedulecore.ufu.domains.resourses;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;

import java.time.Month;
import java.time.MonthDay;

public interface GetSchedules {
    ScheduleModel get(GetSchedulesInput input);
}
