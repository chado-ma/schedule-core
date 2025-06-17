package com.schedulecore.ufu.domains.usecases;

import com.schedulecore.ufu.domains.chainresposability.ScheduleValidator;
import com.schedulecore.ufu.domains.chainresposability.newschedule.GinasioValidator;
import com.schedulecore.ufu.domains.inputs.CreateScheduleInput;
import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.CreateSchedule;
import com.schedulecore.ufu.domains.resourses.GetSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateScheduleImpl implements CreateSchedule {
    private final DatabasePort databasePort;

    @Override
    public void execute(CreateScheduleInput input) {
        validator().check(input.toModel());
        databasePort.saveSchedule(input.toModel());

    }

    public ScheduleValidator validator(){
        return ScheduleValidator.link(
                new GinasioValidator(databasePort)
        );
    }
}
