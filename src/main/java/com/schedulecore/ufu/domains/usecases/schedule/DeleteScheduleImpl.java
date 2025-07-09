package com.schedulecore.ufu.domains.usecases.schedule;

import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.ports.EmailSenderPort;
import com.schedulecore.ufu.domains.resourses.schedule.DeleteSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteScheduleImpl implements DeleteSchedule {
    private final DatabasePort databasePort;
    private final EmailSenderPort emailSenderPort;

    @Override
    public void execute(DeleteScheduleInput input) {
        String email = databasePort.findScheduleByHorarioAndMounthDayAndGinasio(input.getHorario(), input.getData(), input.getGinasio()).map(ScheduleModel::getEmail).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        databasePort.deleteSchedule(input.getHorario(), input.getData(), input.getGinasio(), input.getMatriculaAluno());
        emailSenderPort.sendEmail(email,
                "Seu Agendamento Foi Cancelado - Ginasio: " + input.getGinasio() + " Data: " + input.getData(),
                "Seu Agendamento foi cancelado das " + input.getHorario() + " na data  " + input.getData() + " no ginásio " + input.getGinasio() + ", podendo ter sido cancelado pelo administrador do sistema."
        );
    }
}
