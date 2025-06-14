package com.schedulecore.ufu.domains.inputs;


import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.time.MonthDay;
import java.util.Optional;

@Data
@Builder
public class GetSchedulesInput {
    private MonthDay monthDay;
    private Month month;
    private Optional<CampusEnum> campus;
}
