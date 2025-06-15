package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.MonthDay;

@Entity
@Table(name = "reserva")
@Data
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Time horario;
    private MonthDay data;
    private String ginasio;
    private String responsavel;
    private String curso;
    private String disponibilidade;
    private String campus;
    private String matriculaAluno;
    private String telefone;
    private Integer quantidadePessoas;


    public ScheduleModel toModel() {
        return ScheduleModel.builder()
                .horario(horario)
                .data(data)
                .ginasio(ginasio)
                .responsavel(responsavel)
                .curso(curso)
                .disponibilidade(disponibilidade)
                .campus(campus)
                .build();
    }


}