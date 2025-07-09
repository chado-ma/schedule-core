package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.resourses.ginasio.CreateOrUpdateGinasio;
import com.schedulecore.ufu.domains.resourses.ginasio.DeleteGinasio;
import com.schedulecore.ufu.domains.resourses.restricao.CreateRestricao;
import com.schedulecore.ufu.domains.resourses.restricao.DeleteRestricao;
import com.schedulecore.ufu.infrasctructure.api.request.CreateGinasioRequest;
import com.schedulecore.ufu.infrasctructure.api.request.RestricaoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/adm")
public class AdmController {
    private final CreateOrUpdateGinasio createOrUpdateGinasio;
    private final DeleteGinasio deleteGinasio;
    private final CreateRestricao createRestricao;
    private final DeleteRestricao deleteRestricao;

    @PostMapping("/ginasio")
    public void createOrUpdateGinasio(@RequestBody CreateGinasioRequest request) {
        log.info("Received request for createOrUpdateGinasio: {}", request);
        createOrUpdateGinasio.execute(request.toInput());
    }

    @PostMapping("/ginasio/delete/{id}")
    public void deleteGinasio(@PathVariable("id") String id) {
        log.info("Received request for deleteGinasio: {}", id);
        deleteGinasio.execute(id);
    }

    @PostMapping("/restricao")
    public void createRestricao(@RequestBody RestricaoRequest request) {
        log.info("Received request for createRestricao: {}", request);
        createRestricao.execute(request.toInput());
    }
    @PostMapping("/restricao/delete")
    public void deleteRestricao(@RequestBody RestricaoRequest request) {
        log.info("Received request for deleteRestricao: {}", request);
        deleteRestricao.execute(request.toInput());
    }

}