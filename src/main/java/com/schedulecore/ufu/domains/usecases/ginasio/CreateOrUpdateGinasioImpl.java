package com.schedulecore.ufu.domains.usecases.ginasio;

import com.schedulecore.ufu.domains.inputs.CreateOrUpdateGinasioInput;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.ginasio.CreateOrUpdateGinasio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrUpdateGinasioImpl implements CreateOrUpdateGinasio {
    private final DatabasePort databasePort;
    @Override
    public void execute(CreateOrUpdateGinasioInput input) {
        databasePort.findGinasioById(input.getNome())
                .ifPresentOrElse(
                        ginasio -> databasePort.updateGinasio(input.toModel()),
                        () -> databasePort.saveGinasio(input.toModel())
                );
    }
}
