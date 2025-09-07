package com.schedule.ufu.usecases.schedule;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.schedule.GetUserSchedulesImpl;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUserSchedulesImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private GetUserSchedulesImpl getUserSchedulesImpl;

    @Test
    public void testGetUserSchedules_ReturnsSchedules() {
        String matricula = "12345";

        ScheduleModel schedule1 = ScheduleModel.builder()
                .data(Date.valueOf(LocalDate.of(2025, 9, 6)))
                .ginasio("Ginásio A")
                .campus("Campus UFU")
                .horario(Time.valueOf("10:00:00"))
                .build();

        ScheduleModel schedule2 = ScheduleModel.builder()
                .data(Date.valueOf(LocalDate.of(2025, 9, 7)))
                .ginasio("Ginásio B")
                .campus("Campus UFU")
                .horario(Time.valueOf("11:00:00"))
                .build();

        when(databasePort.findUserSchedules(matricula)).thenReturn(List.of(schedule1, schedule2));

        List<ScheduleModel> result = getUserSchedulesImpl.get(matricula);

        verify(databasePort, times(1)).findUserSchedules(matricula);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Ginásio A", result.get(0).getGinasio());
    }

    @Test
    public void testGetUserSchedules_ReturnsEmptyList() {
        String matricula = "67890";

        when(databasePort.findUserSchedules(matricula)).thenReturn(List.of());

        List<ScheduleModel> result = getUserSchedulesImpl.get(matricula);

        verify(databasePort, times(1)).findUserSchedules(matricula);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetUserSchedules_ThrowsException_WhenMatriculaIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> getUserSchedulesImpl.get(null)
        );
        Assertions.assertEquals("Matrícula não pode ser nula ou vazia", exception.getMessage());
        verify(databasePort, never()).findUserSchedules(anyString());
    }

    @Test
    public void testGetUserSchedules_ThrowsException_WhenMatriculaIsEmpty() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> getUserSchedulesImpl.get("")
        );
        Assertions.assertEquals("Matrícula não pode ser nula ou vazia", exception.getMessage());
        verify(databasePort, never()).findUserSchedules(anyString());
    }
}
