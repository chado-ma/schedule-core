package com.schedulecore.ufu.infrasctructure.database;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.infrasctructure.database.entitys.ReservaEntity;
import com.schedulecore.ufu.infrasctructure.database.repositorys.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class DatabaseAdapter implements DatabasePort {
    private final ReservaRepository reservaRepository;
    @Override
    public List<ScheduleModel> getSchedulesByCampusAndMonthAndDay(Month month, MonthDay monthDay, String campus) {
        return reservaRepository.findAllByCampusAndData(campus, monthDay)
                .stream()
                .map(ReservaEntity::toModel)
                .toList();
    }

    @Override
    public List<ScheduleModel> getSchedulesByMonthAndDay(Month month, MonthDay monthDay) {
        return reservaRepository.findAllByData(monthDay)
                .stream()
                .map(ReservaEntity::toModel)
                .toList();
    }
}
