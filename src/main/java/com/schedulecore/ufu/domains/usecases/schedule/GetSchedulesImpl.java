package com.schedulecore.ufu.domains.usecases.schedule;

import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.schedule.GetSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GetSchedulesImpl implements GetSchedules {
    private final DatabasePort databasePort;

    @Override
    public List<ScheduleModel> get(GetSchedulesInput input) {
        HashMap<Time, ScheduleModel> scheduleMap = new HashMap<>();
        input.getGinasio().map(
                ginasio -> databasePort.findSchedulesByGinasioAndMonthAndDay(input.getData(), ginasio))
                .orElseGet(() -> databasePort.findSchedulesByMonthAndDay(input.getData())).forEach(
                        schedule -> {
                            scheduleMap.put(schedule.getHorario(), schedule);
                        }
                );
        Optional<GinasioModel> model = input.getGinasio().flatMap(databasePort::findGinasioById);
        if (model.isPresent()) {
            Time horario = model.get().getStartTime();
            while (model.get().getEndTime().after(horario)) {
                if (!scheduleMap.containsKey(horario)) {
                    scheduleMap.put(horario, ScheduleModel.builder()
                            .data(input.getData())
                            .ginasio(model.get().getNome())
                            .campus(model.get().getCampus())
                            .horario(horario)
                            .build()
                    );
                }
                horario = Time.valueOf(horario.toLocalTime().plusHours(1));
            }
        }
        List<ScheduleModel> result = new ArrayList<>(scheduleMap.values());
        result.sort(Comparator.comparing(ScheduleModel::getHorario));
        return result;
    }
}
