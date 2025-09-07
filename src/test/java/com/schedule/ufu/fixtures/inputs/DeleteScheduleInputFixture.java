package com.schedule.ufu.fixtures.inputs;

import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class DeleteScheduleInputFixture {
    @Builder.Default
    private Time horario = Time.valueOf(LocalTime.of(10, 0));
    @Builder.Default
    private Date data = Date.valueOf(LocalDate.now());
    @Builder.Default
    private String ginasio = "Ufu Ginasio 1";
    @Builder.Default
    private String matriculaAluno = "123456";

    public DeleteScheduleInput getMock() {
        return DeleteScheduleInput.builder()
                .horario(horario)
                .data(data)
                .ginasio(ginasio)
                .matriculaAluno(matriculaAluno)
                .build();
    }
}

