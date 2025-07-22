package com.schedule.ufu.usecases;

import com.schedule.ufu.fixtures.inputs.GetSchedulesInputFixture;
import com.schedulecore.ufu.domains.inputs.GetSchedulesInput;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.schedule.GetSchedulesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetSchedulesImplTest {
    @Mock
    private DatabasePort databasePort;
    @InjectMocks
    private GetSchedulesImpl getSchedulesImpl;

    @Test
    public void testGetSchedulesExistingGinasio() {
        when(databasePort.findSchedulesByGinasioAndMonthAndDay(any(), any())).thenReturn(List.of());
        when(databasePort.findGinasioById(any())).thenReturn(Optional.ofNullable(GinasioModel.builder()
                .nome("Ufu Ginasio 1")
                .campus("Campus 1")
                .startTime(Time.valueOf("08:00:00"))
                .endTime(Time.valueOf("09:00:00"))
                .build()));
        List<ScheduleModel> list = getSchedulesImpl.get(GetSchedulesInputFixture.builder().build().getMock());
        verify(databasePort).findSchedulesByGinasioAndMonthAndDay(any(), any());
        verify(databasePort).findGinasioById(any());
        Assertions.assertEquals(list.size(),1);
    }

    @Test
    public void testGetSchedulesNotExistingGinasio() {
        when(databasePort.findSchedulesByMonthAndDay(any())).thenReturn(List.of());
        List<ScheduleModel> list = getSchedulesImpl.get(GetSchedulesInputFixture.builder().ginasio(Optional.empty()).build().getMock());
        verify(databasePort).findSchedulesByMonthAndDay(any());
        Assertions.assertEquals(list.size(),0);
    }
}
