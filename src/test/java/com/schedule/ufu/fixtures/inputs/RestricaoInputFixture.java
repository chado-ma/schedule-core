package com.schedule.ufu.fixtures.inputs;

import com.schedulecore.ufu.domains.inputs.RestricaoInput;
import lombok.Builder;
import lombok.Data;


import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
public class RestricaoInputFixture {
    @Builder.Default
    private String ginasio = "Ufu Ginasio 1";
    @Builder.Default
    private Date data = Date.valueOf(LocalDate.now().plusDays(4));
    @Builder.Default
    private String descricao = "Restrição de teste";

    public RestricaoInput getMock() {
        return RestricaoInput.builder()
                .ginasio(ginasio)
                .data(data)
                .descricao(descricao)
                .build();
    }
}

