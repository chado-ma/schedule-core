package com.schedule.ufu.fixtures.inputs;

import com.schedulecore.ufu.domains.inputs.CreateScheduleInput;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class CreateScheduleInputFixture {
    @Builder.Default
    private Time horario = Time.valueOf(LocalTime.of(10, 0));
    @Builder.Default
    private Date data = Date.valueOf(LocalDate.now().plusDays(1));
    @Builder.Default
    private String ginasio = "Ufu Ginasio 1";
    @Builder.Default
    private String responsavel = "João Silva";
    @Builder.Default
    private String curso = "Engenharia";
    @Builder.Default
    private String email = "joao@ufu.br";
    @Builder.Default
    private String campus = "Santa Mônica";
    @Builder.Default
    private String matriculaAluno = "123456";
    @Builder.Default
    private String telefone = "34999999999";
    @Builder.Default
    private Integer quantidadePessoas = 10;

    public CreateScheduleInput getMock() {
        return CreateScheduleInput.builder()
                .horario(horario)
                .data(data)
                .ginasio(ginasio)
                .responsavel(responsavel)
                .curso(curso)
                .email(email)
                .campus(campus)
                .matriculaAluno(matriculaAluno)
                .telefone(telefone)
                .quantidadePessoas(quantidadePessoas)
                .build();
    }
}

