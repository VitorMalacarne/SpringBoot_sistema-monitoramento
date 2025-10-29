package br.edu.utfpr.sistema_monitoramento.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_atuador")
@EqualsAndHashCode(callSuper=false)
public class Atuador extends BaseEntity {
    
    private String tipo;

    private String status;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivo dispositivo;
}