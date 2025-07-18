package com.schedulecore.ufu.infrasctructure.api.request;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.models.enums.AcessEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GerenateAuthRequest {
    @NotNull
    private String matricula;
    @NotNull
    private String nome;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String telefone;

    public UserModel toUserModel() {
        return UserModel.builder()
                .matricula(matricula)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .acess(AcessEnum.USER)
                .build();
    }
}
