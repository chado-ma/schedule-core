package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.RestricaoModel;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.resourses.ginasio.CreateOrUpdateGinasio;
import com.schedulecore.ufu.domains.resourses.ginasio.DeleteGinasio;
import com.schedulecore.ufu.domains.resourses.ginasio.GetGinasios;
import com.schedulecore.ufu.domains.resourses.restricao.CreateRestricao;
import com.schedulecore.ufu.domains.resourses.restricao.DeleteRestricao;
import com.schedulecore.ufu.domains.resourses.restricao.GetRestricoes;
import com.schedulecore.ufu.domains.resourses.schedule.GetAllSchedules;
import com.schedulecore.ufu.infrasctructure.api.request.CreateGinasioRequest;
import com.schedulecore.ufu.infrasctructure.api.request.RestricaoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/adm")
public class AdmController {
    private final CreateOrUpdateGinasio createOrUpdateGinasio;
    private final DeleteGinasio deleteGinasio;
    private final CreateRestricao createRestricao;
    private final DeleteRestricao deleteRestricao;
    private final GetGinasios getGinasios;
    private final GetRestricoes getRestricoes;
    private final GetAllSchedules getAllSchedules;

    @PostMapping("/ginasio")
    public void createOrUpdateGinasio(@RequestBody @Valid CreateGinasioRequest request) {
        log.info("Received request for createOrUpdateGinasio: {}", request);
        createOrUpdateGinasio.execute(request.toInput());
    }

    @PostMapping("/ginasio/delete/{id}")
    public void deleteGinasio(@PathVariable("id") String id) {
        log.info("Received request for deleteGinasio: {}", id);
        deleteGinasio.execute(id);
    }

    @GetMapping("/ginasio")
    public List<GinasioModel> getGinasios() {
        log.info("Received request for getGinasios");
        return getGinasios.execute();
    }

    @GetMapping("/restricao")
    public List<RestricaoModel> getRestricao() {
        log.info("Received request for getRestricao");
        return getRestricoes.execute();
    }

    @GetMapping("/schedule")
    private List<ScheduleModel> getAllSchedules() {
        log.info("Received request for getAllSchedules");
        return getAllSchedules.get();
    }

    @PostMapping("/restricao")
    public void createRestricao(@RequestBody @Valid RestricaoRequest request) {
        log.info("Received request for createRestricao: {}", request);
        createRestricao.execute(request.toInput());
    }
    @PostMapping("/restricao/delete")
    public void deleteRestricao(@RequestBody @Valid RestricaoRequest request) {
        log.info("Received request for deleteRestricao: {}", request);
        deleteRestricao.execute(request.toInput());
    }

}