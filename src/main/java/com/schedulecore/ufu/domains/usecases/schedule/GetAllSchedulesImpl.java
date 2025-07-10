package com.schedulecore.ufu.domains.usecases.schedule;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.schedule.GetAllSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSchedulesImpl implements GetAllSchedules {
    private final DatabasePort databasePort;

    @Override
    public List<ScheduleModel> get() {
        return databasePort.findAllSchedules();
    }
}
