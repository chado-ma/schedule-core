package com.schedulecore.ufu.infrasctructure.emailsender;

import com.schedulecore.ufu.domains.ports.EmailSenderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderAdapter implements EmailSenderPort {
    @Value("${spring.mail.username}")
    private String email;
    private final JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(to);
        mensagem.setSubject(subject);
        mensagem.setText(body);
        mensagem.setFrom(email);
        mailSender.send(mensagem);
        log.info("E-mail enviado com sucesso para: {}, assunto: {}", to, subject);

    }
}
