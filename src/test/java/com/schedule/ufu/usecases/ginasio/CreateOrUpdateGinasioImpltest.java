package com.schedule.ufu.usecases.ginasio;

import com.schedule.ufu.fixtures.inputs.CreateOrUpdateGinasioInputFixture;
import com.schedule.ufu.fixtures.models.GinasioModelFixture;
import com.schedulecore.ufu.domains.inputs.CreateOrUpdateGinasioInput;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.ginasio.CreateOrUpdateGinasioImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrUpdateGinasioImpltest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private CreateOrUpdateGinasioImpl createOrUpdateGinasioImpl;

    @Test
    public void testExecuteWhenGinasioExistsCallsUpdate() {
        CreateOrUpdateGinasioInput input = CreateOrUpdateGinasioInputFixture.builder().build().getMock();
        GinasioModel ginasioModel = GinasioModelFixture.builder().build().getMock();
        when(databasePort.findGinasioById(any()))
                .thenReturn(Optional.of(ginasioModel));

        createOrUpdateGinasioImpl.execute(input);

        verify(databasePort, times(1)).updateGinasio(ginasioModel);
        verify(databasePort, never()).saveGinasio(any());
    }

    @Test
    public void testExecuteWhenGinasioDoesNotExistCallsSave() {
        CreateOrUpdateGinasioInput input = CreateOrUpdateGinasioInputFixture.builder().build().getMock();
        GinasioModel ginasioModel = GinasioModelFixture.builder().build().getMock();

        when(databasePort.findGinasioById(any()))
                .thenReturn(Optional.empty());

        createOrUpdateGinasioImpl.execute(input);

        verify(databasePort, times(1)).saveGinasio(ginasioModel);
        verify(databasePort, never()).updateGinasio(any());
    }
}
