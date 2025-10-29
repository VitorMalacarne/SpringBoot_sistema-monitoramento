package br.edu.utfpr.sistema_monitoramento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.sistema_monitoramento.models.Atuador;

public interface AtuadorRepository extends JpaRepository<Atuador, UUID> {

}
