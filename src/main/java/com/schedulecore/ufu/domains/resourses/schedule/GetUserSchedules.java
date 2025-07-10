package com.schedulecore.ufu.domains.resourses.schedule;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;

import java.util.List;

public interface GetUserSchedules {
    List<ScheduleModel> get(String matricula);
}
