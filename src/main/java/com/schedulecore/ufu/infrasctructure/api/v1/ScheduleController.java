package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import com.schedulecore.ufu.domains.resourses.GetSchedules;
import com.schedulecore.ufu.infrasctructure.api.request.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.MonthDay;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ScheduleController {
    private final GetSchedules getSchedules;

    @GetMapping("/teste")
    public String teste() {
        return "Camping running";
    }

    @GetMapping("/getSchedule")
    public ScheduleModel getScheduleNow(ScheduleRequest request) {
        return  getSchedules.get(GetSchedulesInput.builder()
                .monthDay(MonthDay.of(request.getMonthDay(), request.getMonth()))
                .month(Month.of(request.getMonth()))
                .campus(Optional.ofNullable(CampusEnum.valueOfOrDefault(request.getCampus())))
                .build());
    }
}