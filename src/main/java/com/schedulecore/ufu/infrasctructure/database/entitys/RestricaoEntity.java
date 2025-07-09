package com.schedulecore.ufu.infrasctructure.database.entitys;

import com.schedulecore.ufu.domains.models.RestricaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "restricoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestricaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ginasio;
    private Date data;
    private String descricao;

    public RestricaoEntity(String ginasio, Date data, String descricao) {
        this.ginasio = ginasio;
        this.data = data;
        this.descricao = descricao != null ? descricao : "sem descricao";
    }

    public RestricaoModel toModel() {
        return RestricaoModel.builder()
                .ginasio(ginasio)
                .data(data)
                .descricao(descricao)
                .build();
    }

}
