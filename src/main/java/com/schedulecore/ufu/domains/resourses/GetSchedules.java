package com.schedulecore.ufu.domains.resourses;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;

public interface GetSchedules {
    List<ScheduleModel> get(GetSchedulesInput input);
}
