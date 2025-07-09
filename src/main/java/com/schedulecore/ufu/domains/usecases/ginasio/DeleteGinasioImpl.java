package com.schedulecore.ufu.domains.usecases.ginasio;

import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.ginasio.DeleteGinasio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteGinasioImpl implements DeleteGinasio {
    private final DatabasePort databasePort;

    @Override
    public void execute(String nomeGinasio) {
        databasePort.deleteGinasio(nomeGinasio);
    }
}
