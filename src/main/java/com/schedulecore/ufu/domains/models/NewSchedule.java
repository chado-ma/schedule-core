package com.schedulecore.ufu.domains.models;

import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class NewSchedule {
    private Time horario;
    private Date data;
    private String ginasio;
    private String responsavel;
    private String curso;
    private CampusEnum campus;
    private String matriculaAluno;
    private String telefone;
    private String email;
    private Integer quantidadePessoas;
}
