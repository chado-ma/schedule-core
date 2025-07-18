package com.schedulecore.ufu.infrasctructure.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequest {
    @NotNull
    private String email;
    @NotNull
    private String code;
}
