package br.edu.utfpr.sistema_monitoramento.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_sensor")
@EqualsAndHashCode(callSuper=false)
public class Sensor extends BaseEntity {

    private String tipo;

    private String status;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivo dispositivo;
}
