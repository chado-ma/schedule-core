package com.schedulecore.ufu.domains.inputs;


import com.schedulecore.ufu.domains.models.GinasioModel;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class CreateOrUpdateGinasioInput {
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
