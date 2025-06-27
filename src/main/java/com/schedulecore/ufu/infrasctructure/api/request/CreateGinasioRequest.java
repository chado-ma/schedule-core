package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.inputs.CreateOrUpdateGinasioInput;
import com.schedulecore.ufu.domains.models.enums.CampusEnum;
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
                .campus(CampusEnum.valueOf(campus.toUpperCase()).name())
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
