package com.schedulecore.ufu.domains.usecases.auth;

import com.schedulecore.ufu.domains.resourses.auth.EmailValidatorAcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailValidatorAcessImpl implements EmailValidatorAcess {
    private Map<String, String> emailCodeMap;

    @Override
    public void sendValidationEmail(String email) {
        emailCodeMap.put(email, "Teste");
        System.out.println("Sending validation email to: " + email);
    }

    @Override
    public boolean execute(String email, String code) {
        boolean isValid = emailCodeMap.containsKey(email) &&
                emailCodeMap.get(email).equals(code);
        emailCodeMap.remove(email);
        return isValid;
    }
}
