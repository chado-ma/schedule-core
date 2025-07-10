package com.schedulecore.ufu.domains.usecases.restricao;

import com.schedulecore.ufu.domains.models.RestricaoModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.restricao.GetRestricoes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRestricoesImpl implements GetRestricoes {
    private final DatabasePort databasePort;
    @Override
    public List<RestricaoModel> execute() {
        return databasePort.findAllRestricoes();
    }
}
