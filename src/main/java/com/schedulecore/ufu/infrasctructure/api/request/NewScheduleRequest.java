package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.inputs.CreateScheduleInput;
import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;

@Data
@Builder
public class NewScheduleRequest {

    @NotNull
    private LocalDate data;
    @NotNull
    private Time horario;
    @NotNull
    private String ginasio;
    @NotNull
    private String responsavel;
    @NotNull
    private String curso;
    @NotNull
    private String email;
    @NotNull
    private String campus;
    @NotNull
    private String matriculaAluno;
    @NotNull
    private String telefone;
    @NotNull
    private Integer quantidadePessoas;

    public CreateScheduleInput toInput() {
        return CreateScheduleInput.builder()
                .data(Date.valueOf(data))
                .horario(horario)
                .ginasio(ginasio)
                .responsavel(responsavel)
                .curso(curso)
                .email(email)
                .campus(CampusEnum.valueOf(campus.toUpperCase()))
                .matriculaAluno(matriculaAluno)
                .telefone(telefone)
                .quantidadePessoas(quantidadePessoas)
                .build();
    }
}
