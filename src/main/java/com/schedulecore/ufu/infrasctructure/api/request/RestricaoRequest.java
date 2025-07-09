package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.inputs.RestricaoInput;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
public class RestricaoRequest {
    @NotNull
    private String ginasio;
    @NotNull
    private LocalDate data;
    private String descricao;

    public RestricaoInput toInput() {
        return RestricaoInput.builder()
                .ginasio(ginasio)
                .data(Date.valueOf(data))
                .descricao(descricao)
                .build();
    }


}
