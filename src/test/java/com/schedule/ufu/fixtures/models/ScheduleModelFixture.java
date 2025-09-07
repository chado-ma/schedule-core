package com.schedule.ufu.fixtures.models;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ScheduleModelFixture {
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
    private String email = "joao@ufu.br";

    public ScheduleModel getMock() {
        return ScheduleModel.builder()
                .horario(horario)
                .data(data)
                .ginasio(ginasio)
                .responsavel(responsavel)
                .curso(curso)
                .campus(campus)
                .matriculaAluno(matriculaAluno)
                .email(email)
                .build();
    }
}

