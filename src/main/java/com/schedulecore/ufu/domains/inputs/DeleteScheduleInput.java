package com.schedulecore.ufu.domains.inputs;

import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.MonthDay;

@Data
@Builder
public class DeleteScheduleInput {
    private Time horario;
    private MonthDay data;
    private String ginasio;
    private String matriculaAluno;

}
