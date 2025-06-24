package com.schedulecore.ufu.domains.usecases;

import com.schedulecore.ufu.domains.inputs.CreateGinasioInput;
import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.CreateGinasio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateGinasioImpl implements CreateGinasio {
    private final DatabasePort databasePort;
    @Override
    public void execute(CreateGinasioInput input) {
        databasePort.getGinasioById(input.getNome())
                .ifPresentOrElse(
                        ginasio -> databasePort.updateGinasio(input.toModel()),
                        () -> databasePort.saveGinasio(input.toModel())
                );
    }
}
