package com.schedulecore.ufu.domains.ports;

public interface EmailSenderPort {
    void sendEmail(String to, String subject, String body);
}
