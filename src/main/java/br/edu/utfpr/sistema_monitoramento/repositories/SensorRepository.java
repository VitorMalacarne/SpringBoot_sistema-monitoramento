package br.edu.utfpr.sistema_monitoramento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.sistema_monitoramento.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {

}
