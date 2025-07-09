package com.schedulecore.ufu.domains.inputs;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.Optional;

@Data
@Builder
public class GetSchedulesInput {
    private Date data;
    private Optional<String> ginasio;
}
