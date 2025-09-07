package com.schedule.ufu.usecases.restricao;

import com.schedule.ufu.fixtures.inputs.RestricaoInputFixture;
import com.schedule.ufu.fixtures.models.GinasioModelFixture;
import com.schedulecore.ufu.domains.inputs.RestricaoInput;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.RestricaoModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.restricao.CreateRestricaoImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRestricaoImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private CreateRestricaoImpl createRestricaoImpl;

    @Test
    public void testExecuteValidInputCallsSave() {
        RestricaoInput input = RestricaoInputFixture.builder().ginasio("GinasioA").build().getMock();
        GinasioModel ginasio = GinasioModelFixture.builder().nome("GinasioA").build().getMock();
        when(databasePort.findGinasioById("GinasioA")).thenReturn(Optional.of(ginasio));

        createRestricaoImpl.execute(input);

        verify(databasePort, times(1)).findGinasioById("GinasioA");
        verify(databasePort, times(1)).saveRestricao(input.toModel());
    }

    @Test
    public void testExecuteNullDateThrowsException() {
        RestricaoInput input = mock(RestricaoInput.class);
        when(input.getData()).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createRestricaoImpl.execute(input);
        });

        verify(databasePort, never()).saveRestricao(any());
    }

    @Test
    public void testExecutePastDateThrowsException() {
        RestricaoInput input = RestricaoInputFixture.builder()
                .data(java.sql.Date.valueOf(LocalDate.now().minusDays(4)))
                .ginasio("GinasioA").build().getMock();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createRestricaoImpl.execute(input);
        });

        verify(databasePort, never()).saveRestricao(any());
    }

    @Test
    public void testExecuteGinasioNotFoundThrowsException() {
        RestricaoInput input = RestricaoInputFixture.builder().ginasio("GinasioA").build().getMock();
        when(databasePort.findGinasioById("GinasioA")).thenReturn(Optional.empty());


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createRestricaoImpl.execute(input);
        });

        verify(databasePort, times(1)).findGinasioById("GinasioB");
        verify(databasePort, never()).saveRestricao(any());
    }
}
