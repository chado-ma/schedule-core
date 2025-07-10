package com.schedulecore.ufu.domains.usecases.ginasio;

import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.ginasio.GetGinasios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGinasiosImpl implements GetGinasios {
    private final DatabasePort databasePort;

    @Override
    public List<GinasioModel> execute() {
        return databasePort.findAllGinasios();
    }
}
