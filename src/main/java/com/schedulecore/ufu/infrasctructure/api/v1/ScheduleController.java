package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.resourses.GetSchedules;
import com.schedulecore.ufu.infrasctructure.api.request.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return  getSchedules.get(request.getMonthDay(), request.getMonth());
    }
}
