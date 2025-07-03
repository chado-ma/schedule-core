package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.resourses.CreateOrUpdateGinasio;
import com.schedulecore.ufu.domains.resourses.DeleteGinasio;
import com.schedulecore.ufu.infrasctructure.api.request.CreateGinasioRequest;
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

}