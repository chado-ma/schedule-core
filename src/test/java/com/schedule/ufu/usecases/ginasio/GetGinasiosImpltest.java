package com.schedule.ufu.usecases.ginasio;

import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.ginasio.GetGinasiosImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetGinasiosImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private GetGinasiosImpl getGinasiosImpl;

    @Test
    public void testExecuteReturnsGinasiosList() {
        GinasioModel g1 = mock(GinasioModel.class);
        GinasioModel g2 = mock(GinasioModel.class);

        List<GinasioModel> mockGinasios = List.of(g1, g2);

        when(databasePort.findAllGinasios()).thenReturn(mockGinasios);

        List<GinasioModel> result = getGinasiosImpl.execute();

        verify(databasePort, times(1)).findAllGinasios();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testExecuteReturnsEmptyList() {
        when(databasePort.findAllGinasios()).thenReturn(List.of());

        List<GinasioModel> result = getGinasiosImpl.execute();

        verify(databasePort, times(1)).findAllGinasios();
        Assertions.assertTrue(result.isEmpty());
    }
}
