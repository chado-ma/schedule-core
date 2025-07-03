package com.schedulecore.ufu.domains.inputs;


import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.Month;
import java.time.MonthDay;
import java.util.Optional;

@Data
@Builder
public class GetSchedulesInput {
    private Date data;
    private Optional<String> ginasio;
}
