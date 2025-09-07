package com.schedule.ufu.usecases.schedule;

import com.schedule.ufu.fixtures.inputs.GetSchedulesInputFixture;
import com.schedule.ufu.fixtures.models.GinasioModelFixture;
import com.schedule.ufu.fixtures.models.ScheduleModelFixture;
import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
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

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetSchedulesImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private GetSchedulesImpl getSchedulesImpl;

    @Test
    public void testGetSchedules_WithGinasioAndExistingSchedules() {
        LocalDate data = LocalDate.of(2025, 9, 6);

        // Mock do GinasioModel
        GinasioModel ginasio = GinasioModelFixture.builder()
                .nome("g1")
                .startTime(Time.valueOf("08:00:00"))
                .endTime(Time.valueOf("11:00:00"))
                .build().getMock();

        // Mocks de ScheduleModel
        ScheduleModel schedule1 = ScheduleModelFixture.builder()
                .ginasio("g1")
                .horario(Time.valueOf("10:00:00"))
                .build().getMock();

        // Mocks de ScheduleModel
        ScheduleModel schedule2 =  ScheduleModelFixture.builder()
                .ginasio("g1")
                .horario(Time.valueOf("09:00:00"))
                .build().getMock();
        // Mock do GetSchedulesInput
        GetSchedulesInput input = GetSchedulesInputFixture.builder()
                .data(Date.valueOf(data))
                .ginasio(Optional.of("g1"))
                .build()
                .getMock();
        when(databasePort.findSchedulesByGinasioAndMonthAndDay(Date.valueOf(data), "g1"))
                .thenReturn(List.of(schedule1, schedule2));
        when(databasePort.findGinasioById("g1")).thenReturn(Optional.of(ginasio));

        List<ScheduleModel> result = getSchedulesImpl.get(input);

        verify(databasePort, times(1)).findSchedulesByGinasioAndMonthAndDay(Date.valueOf(data), "g1");
        verify(databasePort, times(1)).findGinasioById("g1");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(Time.valueOf("08:00:00"), result.get(0).getHorario());
        Assertions.assertEquals(Time.valueOf("09:00:00"), result.get(1).getHorario());
        Assertions.assertEquals(Time.valueOf("10:00:00"), result.get(2).getHorario());
    }

    @Test
    public void testGetSchedules_WithoutGinasio() {
        LocalDate data = LocalDate.of(2025, 9, 6);

        // Mock de ScheduleModel
        ScheduleModel schedule = ScheduleModelFixture.builder()
                .ginasio("Outro Ginasio")
                .build().getMock();;
        GetSchedulesInput input = GetSchedulesInputFixture.builder()
                .data(Date.valueOf(data))
                .ginasio(Optional.empty())
                .build()
                .getMock();

        when(databasePort.findSchedulesByMonthAndDay(Date.valueOf(data))).thenReturn(List.of(schedule));

        List<ScheduleModel> result = getSchedulesImpl.get(input);

        verify(databasePort, times(1)).findSchedulesByMonthAndDay(Date.valueOf(data));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Outro Ginasio", result.get(0).getGinasio());
    }

    @Test
    public void testGetSchedules_WithGinasioButNoSchedules() {
        LocalDate data = LocalDate.of(2025, 9, 6);

        // Mock do GinasioModel
        GinasioModel ginasio = mock(GinasioModel.class);
        when(ginasio.getNome()).thenReturn("g2");
        when(ginasio.getNome()).thenReturn("Ginásio Secundário");
        when(ginasio.getCampus()).thenReturn("Campus UFU");
        when(ginasio.getStartTime()).thenReturn(Time.valueOf("14:00:00"));
        when(ginasio.getEndTime()).thenReturn(Time.valueOf("16:00:00"));

        // Mock do GetSchedulesInput
        GetSchedulesInput input = mock(GetSchedulesInput.class);
        when(input.getData()).thenReturn(Date.valueOf(data));
        when(input.getGinasio()).thenReturn(Optional.of("g2"));

        when(databasePort.findSchedulesByGinasioAndMonthAndDay(Date.valueOf(data), "g2"))
                .thenReturn(List.of());
        when(databasePort.findGinasioById("g2")).thenReturn(Optional.of(ginasio));

        List<ScheduleModel> result = getSchedulesImpl.get(input);

        verify(databasePort, times(1)).findSchedulesByGinasioAndMonthAndDay(Date.valueOf(data), "g2");
        verify(databasePort, times(1)).findGinasioById("g2");

        Assertions.assertEquals(2, result.size()); // 14:00 e 15:00
        Assertions.assertTrue(result.stream().allMatch(s -> s.getGinasio().equals("Ginásio Secundário")));
    }
}
