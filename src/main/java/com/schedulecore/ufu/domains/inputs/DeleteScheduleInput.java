package com.schedulecore.ufu.domains.inputs;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class DeleteScheduleInput {
    private Time horario;
    private Date data;
    private String ginasio;
    private String matriculaAluno;

}
