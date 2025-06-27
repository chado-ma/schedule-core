package com.schedulecore.ufu.domains.usecases;

import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.DeleteSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteScheduleImpl implements DeleteSchedule {
    private final DatabasePort databasePort;

    @Override
    public void execute(DeleteScheduleInput input) {
        databasePort.deleteSchedule(input.getHorario(), input.getData(), input.getGinasio(), input.getMatriculaAluno());
    }
}
