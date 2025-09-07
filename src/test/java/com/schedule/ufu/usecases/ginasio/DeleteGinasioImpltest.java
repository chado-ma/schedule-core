package com.schedule.ufu.usecases.ginasio;

import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.ginasio.DeleteGinasioImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteGinasioImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private DeleteGinasioImpl deleteGinasioImpl;

    @Test
    public void testExecuteValidNomeCallsDelete() {
        String nomeGinasio = "Ginásio A";
        doNothing().when(databasePort).deleteGinasio(nomeGinasio);
        deleteGinasioImpl.execute(nomeGinasio);
        verify(databasePort, times(1)).deleteGinasio(nomeGinasio);
    }

    @Test
    public void testExecuteNullNomeThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deleteGinasioImpl.execute(null);
        });
    }

    @Test
    public void testExecuteEmptyNomeThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deleteGinasioImpl.execute("");
        });
    }
}
