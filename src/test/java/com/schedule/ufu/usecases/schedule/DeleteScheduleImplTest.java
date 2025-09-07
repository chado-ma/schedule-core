package com.schedule.ufu.usecases.schedule;

import com.schedule.ufu.fixtures.inputs.DeleteScheduleInputFixture;
import com.schedule.ufu.fixtures.models.ScheduleModelFixture;
import com.schedulecore.ufu.domains.inputs.DeleteScheduleInput;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.ports.EmailSenderPort;
import com.schedulecore.ufu.domains.usecases.schedule.DeleteScheduleImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteScheduleImplTest {

    @Mock
    private DatabasePort databasePort;

    @Mock
    private EmailSenderPort emailSenderPort;

    @InjectMocks
    private DeleteScheduleImpl deleteScheduleImpl;


    @BeforeEach
    void setup() throws Exception {
        setPrivateField(deleteScheduleImpl, "subjectDelete", "[AUTH] ");
        setPrivateField(deleteScheduleImpl, "bodyDelete", "Seu código é: ");
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void testExecute_scheduleFoundAndFutureDate_sendsEmail() {
        Date futureDate = Date.valueOf(LocalDate.now().plusDays(1));
        // Arrange
        DeleteScheduleInput input = DeleteScheduleInputFixture.builder().data(futureDate).build().getMock();

        // Mock do ScheduleModel
        ScheduleModel schedule = ScheduleModelFixture.builder()
                .data(futureDate)
                .build().getMock();
        doNothing().when(emailSenderPort).sendEmail(anyString(), anyString(), anyString());
        when(databasePort.findScheduleByHorarioAndMounthDayAndGinasio(
                input.getHorario(), input.getData(), input.getGinasio()))
                .thenReturn(Optional.of(schedule));
        doNothing().when(databasePort)
                .deleteSchedule(input.getHorario(), input.getData(), input.getGinasio(), input.getMatriculaAluno());
        // Act
        deleteScheduleImpl.execute(input);

        // Assert
        verify(databasePort, times(1))
                .deleteSchedule(any(), any(), any(), any());
        verify(emailSenderPort, times(1))
                .sendEmail(any(), any(), any());
    }


    @Test
    void testExecute_scheduleFoundAndPastDate_doesNotSendEmail() {
        Date pastDate = Date.valueOf(LocalDate.now().minusDays(1));
        // Arrange
        DeleteScheduleInput input = DeleteScheduleInputFixture.builder().data(pastDate).build().getMock();

        // Mock do ScheduleModel
        ScheduleModel schedule = ScheduleModelFixture.builder()
                .data(pastDate)
                .build().getMock();
        when(databasePort.findScheduleByHorarioAndMounthDayAndGinasio(
                input.getHorario(), input.getData(), input.getGinasio()))
                .thenReturn(Optional.of(schedule));
        doNothing().when(databasePort)
                .deleteSchedule(input.getHorario(), input.getData(), input.getGinasio(), input.getMatriculaAluno());
        // Act
        deleteScheduleImpl.execute(input);

        // Assert
        verify(databasePort, times(1))
                .deleteSchedule(any(), any(), any(), any());
        verify(emailSenderPort, never())
                .sendEmail(any(), any(), any());
    }

    @Test
    void testExecute_scheduleNotFound_throwsException() {
        DeleteScheduleInput input = DeleteScheduleInputFixture.builder().build().getMock();

        when(databasePort.findScheduleByHorarioAndMounthDayAndGinasio(
                any(), any(), any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> deleteScheduleImpl.execute(input));

        verify(databasePort, never()).deleteSchedule(any(), any(), any(), any());
        verify(emailSenderPort, never()).sendEmail(any(), any(), any());
    }
}
