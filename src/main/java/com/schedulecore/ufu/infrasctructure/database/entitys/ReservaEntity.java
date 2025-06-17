package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.MonthDay;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Time horario;
    private MonthDay data;
    private String ginasio;
    private String responsavel;
    private String curso;
    private String campus;
    private String matriculaAluno;
    private String telefone;
    private Integer quantidadePessoas;

    public ReservaEntity(Time horario, MonthDay data, String ginasio, String responsavel, String curso, String campus, String matriculaAluno, String telefone, Integer quantidadePessoas) {
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