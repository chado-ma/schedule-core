package com.schedulecore.ufu.infrasctructure.api.v1;

import com.schedulecore.ufu.domains.models.GinasioModel;
import com.schedulecore.ufu.domains.models.RestricaoModel;
import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.resourses.auth.Authenticate;
import com.schedulecore.ufu.domains.resourses.auth.EmailValidatorAcess;
import com.schedulecore.ufu.domains.resourses.auth.GenerateAuth;
import com.schedulecore.ufu.domains.resourses.ginasio.CreateOrUpdateGinasio;
import com.schedulecore.ufu.domains.resourses.ginasio.DeleteGinasio;
import com.schedulecore.ufu.domains.resourses.ginasio.GetGinasios;
import com.schedulecore.ufu.domains.resourses.restricao.CreateRestricao;
import com.schedulecore.ufu.domains.resourses.restricao.DeleteRestricao;
import com.schedulecore.ufu.domains.resourses.restricao.GetRestricoes;
import com.schedulecore.ufu.domains.resourses.schedule.GetAllSchedules;
import com.schedulecore.ufu.infrasctructure.api.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/auth")
public class AuthController {
    private final Authenticate authenticate;
    private final EmailValidatorAcess emailValidatorAcess;
    private final GenerateAuth generateAuth;

    @PostMapping("")
    public String generateAuth(@RequestBody @Valid GerenateAuthRequest request) {
        log.info("Received request for generateAuth: {}", request);
        return generateAuth.execute(request.toUserModel());
    }

    @PostMapping("/validate")
    public UserModel authenticate(@RequestBody @Valid TokenRequest request) {
        log.info("Received request for authenticate: {}", request);
        return authenticate.execute(request.getToken());
    }

    @PostMapping("/email/validate")
    public void emailValidate(@RequestBody @Valid EmailRequest request) {
        log.info("Received request for emailValidate: {}", request);
        emailValidatorAcess.execute(request.getEmail(), request.getCode());
    }

    @PostMapping("/email/send/{email}")
    public void emailCodeSend(@PathVariable("email") String email) {
        log.info("Received request for emailCodeSend: {}", email);
        emailValidatorAcess.sendValidationEmail(email);
    }


}