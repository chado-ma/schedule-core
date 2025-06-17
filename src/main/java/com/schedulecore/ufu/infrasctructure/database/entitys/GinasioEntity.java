package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.GinasioModel;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Optional;

@Entity
@Table(name = "ginasios")
public class GinasioEntity {
    @Id
    private String nome;
    private String campus;
    private Time startTime;
    private Time endTime;

    public Optional<GinasioModel> toModel() {
        return Optional.of(GinasioModel.builder()
                .nome(nome)
                .campus(campus)
                .startTime(startTime)
                .endTime(endTime)
                .build());
    }
}
