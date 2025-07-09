package com.schedulecore.ufu.domains.usecases.restricao;

import com.schedulecore.ufu.domains.inputs.RestricaoInput;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.restricao.CreateRestricao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreateRestricaoImpl implements CreateRestricao {
    private final DatabasePort databasePort;

    @Override
    public void execute(RestricaoInput input) {
        if (input.getData() == null)
            throw new IllegalArgumentException("Date cannot be null");
        if(input.getData().before(Date.from(Instant.now())))
            throw new IllegalArgumentException("Date cannot be in the past, data invalid: " + input.getData());
        databasePort.findGinasioById(input.getGinasio())
                .orElseThrow(() -> new IllegalArgumentException("Ginasio not found with id: " + input.getGinasio()));
        databasePort.saveRestricao(input.toModel());
    }
}
