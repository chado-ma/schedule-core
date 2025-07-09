package com.schedulecore.ufu.domains.usecases.schedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.chainresponsibility.newschedule.*;
import com.schedulecore.ufu.domains.inputs.CreateScheduleInput;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.schedule.CreateSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                new DateValidator(),
                new HorarioValidator(),
                new GinasioValidator(databasePort),
                new RestricaoValidator(databasePort),
                new ScheduleDuplicated(databasePort)
        );
    }
}
