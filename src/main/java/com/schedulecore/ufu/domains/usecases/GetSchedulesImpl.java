package com.schedulecore.ufu.domains.usecases;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.GetSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSchedulesImpl implements GetSchedules {
    private final DatabasePort databasePort;

    @Override
    public List<ScheduleModel> get(GetSchedulesInput input) {
        return input.getCampus().map(campus -> databasePort.getSchedulesByCampusAndMonthAndDay(input.getMonth(), input.getMonthDay(), campus.name()))
                .orElseGet(() -> databasePort.getSchedulesByMonthAndDay(input.getMonth(), input.getMonthDay()));
    }
}
