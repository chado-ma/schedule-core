package com.schedulecore.ufu.domains.inputs;

import com.schedulecore.ufu.domains.models.NewSchedule;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class CreateScheduleInput {
    private Time horario;
    private Date data;
    private String ginasio;
    private String responsavel;
    private String curso;
    private String email;
    private String campus;
    private String matriculaAluno;
    private String telefone;
    private Integer quantidadePessoas;

    public NewSchedule toModel(){
        return NewSchedule.builder()
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
