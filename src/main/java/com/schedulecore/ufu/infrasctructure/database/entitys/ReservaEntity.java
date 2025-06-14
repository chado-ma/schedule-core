package com.schedulecore.ufu.infrasctructure.database.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reserva")
@Data
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horario;
    private String nome;
    private String responsavel;
    private String curso;
    private String disponibilidade;
    private String campus;
    private String matriculaAluno;
    private String telefone;
    private Integer quantidadePessoas;
}