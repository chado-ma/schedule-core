package com.schedulecore.ufu.domains.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class GinasioModel {
    private String nome;
    private String campus;
    private Time startTime;
    private Time endTime;
}
