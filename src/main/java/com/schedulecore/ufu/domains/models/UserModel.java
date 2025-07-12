package com.schedulecore.ufu.domains.models;

import com.schedulecore.ufu.domains.models.enums.AcessEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String matricula;
    private String nome;
    private String email;
    private String telefone;
    private AcessEnum acess;
}
