package com.schedulecore.ufu.domains.models;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RestricaoModel {
    private String ginasio;
    private Date data;
    private String descricao;

}
