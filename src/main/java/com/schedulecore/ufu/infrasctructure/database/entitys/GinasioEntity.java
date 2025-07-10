package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.GinasioModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ginasios")
public class GinasioEntity {
    @Id
    private String nome;
    private String campus;
    private Time startTime;
    private Time endTime;

    public GinasioModel toModel() {
        return GinasioModel.builder()
                .nome(nome)
                .campus(campus)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
