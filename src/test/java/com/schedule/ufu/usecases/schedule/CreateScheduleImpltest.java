package com.schedule.ufu.usecases.schedule;

import com.schedule.ufu.fixtures.inputs.CreateScheduleInputFixture;
import com.schedule.ufu.fixtures.models.GinasioModelFixture;
import com.schedule.ufu.fixtures.models.RestricaoModelFixture;
import com.schedule.ufu.fixtures.models.ScheduleModelFixture;
import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.inputs.CreateScheduleInput;
import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.schedule.CreateScheduleImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateScheduleImpltest {
    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private CreateScheduleImpl createScheduleImpl;

    @Test
    void execute_chamaValidatorCriaSchedule() {
        // Arrange
        CreateScheduleInput input = CreateScheduleInputFixture.builder().build().getMock();
        when(databasePort.findGinasioById(anyString())).thenReturn(Optional.of(
                GinasioModelFixture.builder().build().getMock()
        ));
        when(databasePort.findRestricaoByGinasioAndData(anyString(), any())).thenReturn(Optional.empty());
        when(databasePort.findScheduleByHorarioAndMounthDayAndGinasio(any(), any(), anyString())).thenReturn(Optional.empty());

        // Act
        createScheduleImpl.execute(input);

        // Assert
        verify(databasePort, times(1)).saveSchedule(any());
    }

    @Test
    void execute_chamaValidorEFalhaGinasio() {
        // Arrange
        CreateScheduleInput input = CreateScheduleInputFixture.builder().build().getMock();
        when(databasePort.findGinasioById(anyString())).thenReturn(
                Optional.empty()
        );
        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            createScheduleImpl.execute(input);
        });

        // Assert
        verify(databasePort, times(0)).saveSchedule(any());
    }

    @Test
    void execute_chamaValidorEFalhaRestricao() {
        // Arrange
        CreateScheduleInput input = CreateScheduleInputFixture.builder().build().getMock();
        when(databasePort.findGinasioById(anyString())).thenReturn(
                Optional.ofNullable(GinasioModelFixture.builder().build().getMock())
        );
        when(databasePort.findRestricaoByGinasioAndData(anyString(), any())).thenReturn(
                Optional.of(RestricaoModelFixture.builder().build().getMock()));
        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            createScheduleImpl.execute(input);
        });

        // Assert
        verify(databasePort, times(0)).saveSchedule(any());
    }

    @Test
    void execute_chamaValidorEFalhaDuplicated() {
        // Arrange
        CreateScheduleInput input = CreateScheduleInputFixture.builder().build().getMock();
        when(databasePort.findGinasioById(anyString())).thenReturn(
                Optional.ofNullable(GinasioModelFixture.builder().build().getMock())
        );
        when(databasePort.findRestricaoByGinasioAndData(anyString(), any())).thenReturn(Optional.empty());
        when(databasePort.findScheduleByHorarioAndMounthDayAndGinasio(any(), any(), anyString())).thenReturn(
                Optional.of(ScheduleModelFixture.builder().build().getMock()));

        // Act
        assertThrows(IllegalArgumentException.class, () -> {
            createScheduleImpl.execute(input);
        });

        // Assert
        verify(databasePort, times(0)).saveSchedule(any());
    }
}

