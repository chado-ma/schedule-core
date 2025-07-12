package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.models.enums.AcessEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private String email;
    private String matricula;
    private String nome;
    private String telefone;
    private String role;

    public UserModel toModel() {
        return UserModel.builder()
                .matricula(matricula)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .acess(AcessEnum.valueOf(role))
                .build();
    }
}
