package br.edu.utfpr.sistema_monitoramento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.sistema_monitoramento.models.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

}
