package com.schedule.ufu.fixtures.models;

import com.schedulecore.ufu.domains.models.RestricaoModel;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
public class RestricaoModelFixture {
    @Builder.Default
    private final String ginasio = "Ufu Ginasio 1";
    @Builder.Default
    private final Date data = Date.valueOf(LocalDate.now());
    @Builder.Default
    private final String descricao = "Restrição de teste";

    public RestricaoModel getMock() {
        return RestricaoModel.builder()
                .ginasio(ginasio)
                .data(data)
                .descricao(descricao)
                .build();
    }
}

