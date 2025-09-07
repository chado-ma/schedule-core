package com.schedule.ufu.fixtures.models;

import com.schedulecore.ufu.domains.models.NewSchedule;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class NewScheduleFixture {
    @Builder.Default
    private Time horario = Time.valueOf(LocalTime.of(10, 0));
    @Builder.Default
    private Date data = Date.valueOf(LocalDate.now());
    @Builder.Default
    private String ginasio = "Ufu Ginasio 1";
    @Builder.Default
    private String responsavel = "João Silva";
    @Builder.Default
    private String curso = "Engenharia";
    @Builder.Default
    private String campus = "Santa Mônica";
    @Builder.Default
    private String matriculaAluno = "123456";
    @Builder.Default
    private String telefone = "34999999999";
    @Builder.Default
    private String email = "joao@ufu.br";
    @Builder.Default
    private Integer quantidadePessoas = 10;

    public NewSchedule getMock() {
        return NewSchedule.builder()
                .horario(horario)
                .data(data)
                .ginasio(ginasio)
                .responsavel(responsavel)
                .curso(curso)
                .campus(campus)
                .matriculaAluno(matriculaAluno)
                .telefone(telefone)
                .email(email)
                .quantidadePessoas(quantidadePessoas)
                .build();
    }
}

