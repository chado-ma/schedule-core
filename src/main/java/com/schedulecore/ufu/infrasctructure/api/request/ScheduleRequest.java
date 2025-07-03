package com.schedulecore.ufu.infrasctructure.api.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScheduleRequest {
    @NotNull
    private LocalDate data;
    private String ginasio;
}
