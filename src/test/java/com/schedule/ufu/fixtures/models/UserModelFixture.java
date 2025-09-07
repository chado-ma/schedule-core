package com.schedule.ufu.fixtures.models;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.models.enums.AcessEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModelFixture {
    @Builder.Default
    private String matricula = "123456";
    @Builder.Default
    private String nome = "João Silva";
    @Builder.Default
    private String email = "joao@ufu.br";
    @Builder.Default
    private String telefone = "34999999999";
    @Builder.Default
    private AcessEnum acess = AcessEnum.USER;

    public UserModel getMock() {
        return UserModel.builder()
                .matricula(matricula)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .acess(acess)
                .build();
    }
}

