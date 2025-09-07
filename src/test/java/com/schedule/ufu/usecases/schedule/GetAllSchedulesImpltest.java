package com.schedule.ufu.usecases.schedule;

import com.schedule.ufu.fixtures.models.ScheduleModelFixture;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.schedule.GetAllSchedulesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllSchedulesImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private GetAllSchedulesImpl getAllSchedulesImpl;

    @Test
    void testGetReturnsSchedulesList() {
        // Arrange
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
        List<ScheduleModel> mockSchedules = List.of(schedule1, schedule2);

        when(databasePort.findAllSchedules()).thenReturn(mockSchedules);

        // Act
        List<ScheduleModel> result = getAllSchedulesImpl.get();

        // Assert
        verify(databasePort, times(1)).findAllSchedules();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetReturnsEmptyList() {
        // Arrange
        when(databasePort.findAllSchedules()).thenReturn(List.of());

        // Act
        List<ScheduleModel> result = getAllSchedulesImpl.get();

        // Assert
        verify(databasePort, times(1)).findAllSchedules();
        Assertions.assertTrue(result.isEmpty());
    }
}
