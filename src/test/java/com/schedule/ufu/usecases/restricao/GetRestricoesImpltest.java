package com.schedule.ufu.usecases.restricao;

import com.schedulecore.ufu.domains.models.RestricaoModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.restricao.GetRestricoesImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetRestricoesImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private GetRestricoesImpl getRestricoesImpl;

    @Test
    public void testExecuteReturnsRestricoesList() {
        RestricaoModel r1 = mock(RestricaoModel.class);
        RestricaoModel r2 = mock(RestricaoModel.class);

        List<RestricaoModel> mockRestricoes = List.of(r1, r2);

        when(databasePort.findAllRestricoes()).thenReturn(mockRestricoes);

        List<RestricaoModel> result = getRestricoesImpl.execute();

        verify(databasePort, times(1)).findAllRestricoes();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testExecuteReturnsEmptyList() {
        when(databasePort.findAllRestricoes()).thenReturn(List.of());

        List<RestricaoModel> result = getRestricoesImpl.execute();

        verify(databasePort, times(1)).findAllRestricoes();
        Assertions.assertTrue(result.isEmpty());
    }
}
