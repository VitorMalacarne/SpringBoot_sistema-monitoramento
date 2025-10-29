package br.edu.utfpr.sistema_monitoramento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.sistema_monitoramento.models.Aviario;

public interface AviarioRepository extends JpaRepository<Aviario, UUID> {

}
