package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.MonthDay;

@Data
@Builder
public class DeleteScheduleRequest {
    @NotNull
    private Time horario;
    @NotNull
    private LocalDate data;
    @NotBlank
    private String ginasio;
    @NotBlank
    private String matriculaAluno;


    public DeleteScheduleInput toInput() {
        return DeleteScheduleInput.builder()
                .horario(horario)
                .data(Date.valueOf(data))
                .ginasio(ginasio)
                .matriculaAluno(matriculaAluno)
                .build();
    }
}
