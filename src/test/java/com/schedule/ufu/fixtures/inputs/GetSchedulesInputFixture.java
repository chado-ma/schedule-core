package com.schedule.ufu.fixtures.inputs;


import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class GetSchedulesInputFixture {
    @Builder.Default
    private Date data = Date.valueOf(LocalDate.now());
    @Builder.Default
    private Optional<String> ginasio = Optional.of("Ufu Ginasio 1");

    public GetSchedulesInput getMock() {
    return GetSchedulesInput.builder()
            .data(data)
            .ginasio(ginasio)
            .build();
    }
}
