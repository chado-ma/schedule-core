package com.schedule.ufu.usecases.restricao;

import com.schedulecore.ufu.domains.inputs.RestricaoInput;
import com.schedulecore.ufu.domains.models.RestricaoModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.restricao.DeleteRestricaoImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteRestricaoImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private DeleteRestricaoImpl deleteRestricaoImpl;

    @Test
    public void testExecuteCallsDeleteRestricao() {
        RestricaoInput input = mock(RestricaoInput.class);
        RestricaoModel restricaoModel = mock(RestricaoModel.class);

        when(input.toModel()).thenReturn(restricaoModel);

        deleteRestricaoImpl.execute(input);

        verify(databasePort, times(1)).deleteRestricao(restricaoModel);
    }
}
