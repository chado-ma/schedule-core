package com.schedulecore.ufu.domains.usecases.schedule;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.schedule.GetUserSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserSchedulesImpl implements GetUserSchedules {
    private final DatabasePort databasePort;

    @Override
    public List<ScheduleModel> get(String matricula) {
        if(matricula == null || matricula.isEmpty()) {
            throw new IllegalArgumentException("Matrícula não pode ser nula ou vazia");
        }
        return databasePort.findUserSchedules(matricula);
    }
}
