package br.edu.utfpr.sistema_monitoramento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.sistema_monitoramento.models.Lote;
import java.util.UUID;

public interface LoteRepository extends JpaRepository<Lote, UUID> {

}
