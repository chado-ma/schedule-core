package com.schedulecore.ufu.domains.models;


import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.List;

@Data
@Builder
public class ScheduleModel {
    private Time horario;
    private MonthDay data;
    private String ginasio;
    private String responsavel;
    private String curso;
    private String disponibilidade;
    private String campus;
}
