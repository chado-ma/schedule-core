package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.MonthDay;

@Data
@Builder
public class DeleteScheduleRequest {
    @NotBlank
    private Time horario;
    @NotNull
    private Integer monthDay;
    @NotNull
    private Integer month;
    @NotBlank
    private String ginasio;
    @NotBlank
    private String matriculaAluno;


    public DeleteScheduleInput toInput() {
        return DeleteScheduleInput.builder()
                .horario(horario)
                .data(MonthDay.of(month, monthDay))
                .ginasio(ginasio)
                .matriculaAluno(matriculaAluno)
                .build();
    }
}
