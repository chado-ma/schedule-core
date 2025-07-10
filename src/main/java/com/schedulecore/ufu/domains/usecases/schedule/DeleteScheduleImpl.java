package com.schedulecore.ufu.domains.usecases.schedule;

import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.ports.EmailSenderPort;
import com.schedulecore.ufu.domains.resourses.schedule.DeleteSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DeleteScheduleImpl implements DeleteSchedule {
    private final DatabasePort databasePort;
    private final EmailSenderPort emailSenderPort;
    @Value("${spring.mail.subjectDelete}")
    private String subjectDelete;
    @Value("${spring.mail.bodyDelete}")
    private String bodyDelete;

    @Override
    public void execute(DeleteScheduleInput input) {
        String email = databasePort.findScheduleByHorarioAndMounthDayAndGinasio(input.getHorario(), input.getData(), input.getGinasio()).map(ScheduleModel::getEmail).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        databasePort.deleteSchedule(input.getHorario(), input.getData(), input.getGinasio(), input.getMatriculaAluno());
        if(input.getData().after(Date.from(Instant.now()))) {
            emailSenderPort.sendEmail(email,
                    subjectDelete + "Data: " + input.getData() + " Horário: " + input.getHorario() + " Ginasio: " + input.getGinasio(),
                    bodyDelete + input);
        }


    }
}
