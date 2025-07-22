package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.resourses.ginasio.GetGinasios;
import com.schedulecore.ufu.domains.resourses.schedule.CreateSchedule;
import com.schedulecore.ufu.domains.resourses.schedule.DeleteSchedule;
import com.schedulecore.ufu.domains.resourses.schedule.GetSchedules;
import com.schedulecore.ufu.domains.resourses.schedule.GetUserSchedules;
import com.schedulecore.ufu.infrasctructure.api.request.DeleteScheduleRequest;
import com.schedulecore.ufu.infrasctructure.api.request.NewScheduleRequest;
import com.schedulecore.ufu.infrasctructure.api.request.ScheduleRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/schedule")
public class ScheduleController {
    private final GetSchedules getSchedules;
    private final CreateSchedule createSchedule;
    private final DeleteSchedule deleteSchedule;
    private final GetUserSchedules getUserSchedules;
    private final GetGinasios getGinasios;

    @GetMapping("/teste")
    public String teste() {
        log.info("Received request for teste endpoint");
        return "Schedule UFU Core is running";
    }

    @GetMapping("")
    public List<ScheduleModel> getSchedule(@RequestBody @Valid ScheduleRequest request) {
        log.info("Received request for getSchedule: {}", request);
        return getSchedules.get(GetSchedulesInput.builder()
                .data(Date.valueOf(request.getData()))
                .ginasio(Optional.ofNullable(request.getGinasio()))
                .build());
    }

    @PostMapping("")
    public void createShedule(@RequestBody @Valid NewScheduleRequest request) {
        log.info("Received request for createShedule: {}", request);
        createSchedule.execute(request.toInput());
    }

    @PostMapping("/delete")
    public void deleteSchedule(@RequestBody @Valid DeleteScheduleRequest request) {
        log.info("Received request for deleteSchedule: {}", request);
        deleteSchedule.execute(request.toInput());
    }

    @GetMapping("/{matricula}")
    public List<ScheduleModel> getUserSchedule(@PathVariable("matricula") String request) {
        log.info("Received request for getUserSchedule: {}", request);
        return getUserSchedules.get(request);
    }

    @GetMapping("/ginasio")
    public List<GinasioModel> getGinasios() {
        log.info("Received request for getGinasios");
        return getGinasios.execute();
    }


}