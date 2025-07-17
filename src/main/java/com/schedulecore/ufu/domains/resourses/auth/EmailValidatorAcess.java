package com.schedulecore.ufu.domains.resourses.auth;

public interface EmailValidatorAcess {
     void sendValidationEmail(String email);
     boolean execute(String email, String code);
}
