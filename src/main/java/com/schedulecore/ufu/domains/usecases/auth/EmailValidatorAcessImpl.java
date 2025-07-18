package com.schedulecore.ufu.domains.usecases.auth;

import com.schedulecore.ufu.domains.ports.EmailSenderPort;
import com.schedulecore.ufu.domains.resourses.auth.EmailValidatorAcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailValidatorAcessImpl implements EmailValidatorAcess {
    private static final Integer CODE_LENGTH = 6;
    private final EmailSenderPort emailSenderPort;
    @Value("${spring.mail.subjectAuth}")
    private String subjectAuth;
    @Value("${spring.mail.bodyAuth}")
    private String bodyAuth;
    private final Map<String, String> emailCodeMap = new ConcurrentHashMap<>();

    @Override
    public void sendValidationEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String code = gerarCodigoNumerico();
        if (!emailCodeMap.containsKey(email)) {
            emailSenderPort.sendEmail(email,
                    subjectAuth + "Email de validação",
                    bodyAuth + code);
            emailCodeMap.put(email, code);
            log.info("Validation email sent to {} with code start {}", email, code.substring(0, 2));
        } else {
            log.warn("Email {} already has a validation code, not sending again", email);
        }
    }

    @Override
    public void execute(String email, String code) {
        boolean isValid = emailCodeMap.containsKey(email) &&
                emailCodeMap.get(email).equals(code);
        if (!isValid) throw new AuthenticationCredentialsNotFoundException("Code inválido");
       emailCodeMap.remove(email);
    }

    private String gerarCodigoNumerico() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
