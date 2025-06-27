package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.resourses.CreateOrUpdateGinasio;
import com.schedulecore.ufu.infrasctructure.api.request.CreateGinasioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/adm")
public class AdmController {
    private final CreateOrUpdateGinasio createOrUpdateGinasio;

    @PostMapping("/ginasio")
    public void createOrUpdateGinasio(@RequestBody CreateGinasioRequest request) {
        log.info("Received request for createOrUpdateGinasio: {}", request);
        createOrUpdateGinasio.execute(request.toInput());
    }

}