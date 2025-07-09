package com.schedulecore.ufu.domains.usecases.restricao;

import com.schedulecore.ufu.domains.inputs.RestricaoInput;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.restricao.DeleteRestricao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRestricaoImpl implements DeleteRestricao {
    private final DatabasePort databasePort;

    @Override
    public void execute(RestricaoInput input) {
        databasePort.deleteRestricao(input.toModel());
    }
}
