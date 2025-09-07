package com.schedule.ufu.fixtures.models;

import com.schedulecore.ufu.domains.models.GinasioModel;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;

@Data
@Builder
public class GinasioModelFixture {
    @Builder.Default
    private String nome = "Ufu Ginasio 1";
    @Builder.Default
    private String campus = "Santa Mônica";
    @Builder.Default
    private Time startTime = Time.valueOf(LocalTime.of(7, 0));
    @Builder.Default
    private Time endTime = Time.valueOf(LocalTime.of(22, 0));

    public GinasioModel getMock() {
        return GinasioModel.builder()
                .nome(nome)
                .campus(campus)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}

