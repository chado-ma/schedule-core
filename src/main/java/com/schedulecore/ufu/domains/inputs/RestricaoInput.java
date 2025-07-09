package com.schedulecore.ufu.domains.inputs;

import com.schedulecore.ufu.domains.models.RestricaoModel;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RestricaoInput {
    private String ginasio;
    private Date data;
    private String descricao;

    public RestricaoModel toModel() {
        return RestricaoModel.builder()
                .ginasio(ginasio)
                .data(data)
                .descricao(descricao)
                .build();
    }
}
