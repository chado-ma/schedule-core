package com.schedulecore.ufu.domains.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String matricula;
    private String nome;
    private String email;
    private String telefone;
}
