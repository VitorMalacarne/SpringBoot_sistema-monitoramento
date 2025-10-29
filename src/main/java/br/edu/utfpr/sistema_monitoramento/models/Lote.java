package br.edu.utfpr.sistema_monitoramento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_lote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
public class Lote extends BaseEntity {

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "aviario_id", nullable = false)
    private Aviario aviario;
}
