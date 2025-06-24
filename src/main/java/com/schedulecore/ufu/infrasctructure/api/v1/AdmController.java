package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.resourses.CreateGinasio;
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
@RequestMapping("/adm/v1")
public class AdmController {
    private final CreateGinasio createGinasio;

    @PostMapping("/ginasio")
    public void createShedule(@RequestBody CreateGinasioRequest request) {
        log.info("Received request for createShedule: {}", request);
        createGinasio.execute(request.toInput());
    }

}