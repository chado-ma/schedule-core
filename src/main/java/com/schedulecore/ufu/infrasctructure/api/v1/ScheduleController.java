package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.inputs.CreateScheduleInput;
import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.models.enums.CampusEnum;
import com.schedulecore.ufu.domains.resourses.CreateSchedule;
import com.schedulecore.ufu.domains.resourses.GetSchedules;
import com.schedulecore.ufu.infrasctructure.api.request.NewScheduleRequest;
import com.schedulecore.ufu.infrasctructure.api.request.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1")
public class ScheduleController {
    private final GetSchedules getSchedules;
    private final CreateSchedule createSchedule;

    @GetMapping("/teste")
    public String teste() {
        log.info("Received request for teste endpoint");
        return "Schedule UFU Core is running";
    }

    @GetMapping("/schedule")
    public List<ScheduleModel> getSchedule(@RequestBody ScheduleRequest request) {
        log.info("Received request for getSchedule: {}", request);
        return getSchedules.get(GetSchedulesInput.builder()
                .monthDay(MonthDay.of(request.getMonth(), request.getMonthDay()))
                .month(Month.of(request.getMonth()))
                .campus(Optional.ofNullable(CampusEnum.valueOfOrDefault(request.getCampus())))
                .build());
    }

    @PostMapping("/schedule")
    public void createShedule(@RequestBody NewScheduleRequest request) {
        log.info("Received request for createShedule: {}", request);
        createSchedule.execute(request.toInput());
    }

}