package com.schedulecore.ufu.domains.resourses.auth;

public interface EmailValidatorAcess {
     void sendValidationEmail(String email);
     void execute(String email, String code);
}
