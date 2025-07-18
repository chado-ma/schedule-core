package com.schedulecore.ufu.infrasctructure;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.models.enums.AcessEnum;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdmCreation implements CommandLineRunner {
    private final DatabasePort databasePort;
    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void run(String... args) throws Exception {
        databasePort.findUserByEmail(email)
                .ifPresentOrElse(
                        user -> {
                            user.setAcess(AcessEnum.ADMIN);
                            databasePort.saveUserOrUpdateAdm(user);
                        },
                        () -> databasePort.saveUserOrUpdateAdm(
                                UserModel.builder()
                                        .email(email)
                                        .nome("Administrador")
                                        .matricula("00000ADM000")
                                        .telefone("0000000000")
                                        .acess(AcessEnum.ADMIN)
                                        .build()
                        )
                );
        log.info("Administrador criado ou atualizado com sucesso com o email: {}", email.substring(0,3));
    }

}
