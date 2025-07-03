package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Time horario;
    private Date data;
    private String ginasio;
    private String responsavel;
    private String curso;
    private String campus;
    private String matriculaAluno;
    private String telefone;
    private Integer quantidadePessoas;

    public ReservaEntity(Time horario, Date data, String ginasio, String responsavel, String curso, String campus, String matriculaAluno, String telefone, Integer quantidadePessoas) {
        this.horario = horario;
        this.data = data;
        this.ginasio = ginasio;
        this.responsavel = responsavel;
        this.curso = curso;
        this.campus = campus;
        this.matriculaAluno = matriculaAluno;
        this.telefone = telefone;
        this.quantidadePessoas = quantidadePessoas;
    }


    public ScheduleModel toModel() {
        return ScheduleModel.builder()
                .horario(horario)
                .data(data)
                .ginasio(ginasio)
                .responsavel(responsavel)
                .curso(curso)
                .campus(campus)
                .build();
    }


}