package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.inputs.CreateOrUpdateGinasioInput;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class CreateGinasioRequest {
    @NotNull
    private String nome;
    @NotNull
    private String campus;
    @NotNull
    private Time startTime;
    @NotNull
    private Time endTime;

    public CreateOrUpdateGinasioInput toInput() {
        return CreateOrUpdateGinasioInput.builder()
                .nome(nome)
                .campus(campus)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
