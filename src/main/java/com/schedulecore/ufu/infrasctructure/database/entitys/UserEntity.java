package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private String matricula;
    private String nome;
    private String email;
    private String telefone;

    public UserModel toModel() {
        return UserModel.builder()
                .matricula(matricula)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .build();
    }
}
