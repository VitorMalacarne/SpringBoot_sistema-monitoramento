package br.edu.utfpr.sistema_monitoramento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "tb_pessoa")
public class Pessoa extends BaseEntity {

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 200)
    private String email;

}
