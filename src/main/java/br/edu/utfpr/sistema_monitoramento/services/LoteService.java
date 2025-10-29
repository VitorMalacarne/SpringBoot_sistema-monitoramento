package br.edu.utfpr.sistema_monitoramento.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.edu.utfpr.sistema_monitoramento.dtos.LoteDTO;
import br.edu.utfpr.sistema_monitoramento.exception.NotFoundException;
import br.edu.utfpr.sistema_monitoramento.models.Aviario;
import br.edu.utfpr.sistema_monitoramento.models.Lote;
import br.edu.utfpr.sistema_monitoramento.repositories.AviarioRepository;
import br.edu.utfpr.sistema_monitoramento.repositories.LoteRepository;

@Service
public class LoteService {

    private final LoteRepository repository;
    @Autowired
    private AviarioRepository aviarioRepository;

    public LoteService(LoteRepository repository) {
        this.repository = repository;
    }

    public Lote save(LoteDTO dto) {
        var lote = new Lote();
        Aviario aviario = aviarioRepository.findById(dto.aviarioId()).orElseThrow(() -> new NotFoundException("Aviário com ID: " + dto.aviarioId() + " não encontrado."));
        BeanUtils.copyProperties(dto, lote);
        lote.setAviario(aviario);
        return repository.save(lote);
    }

    public Page<Lote> findAll(int pagina, int tamanho) {
        return repository.findAll(PageRequest.of(pagina, tamanho));
    }

    public Lote findById(String id) {
        UUID uuid = UUID.fromString(id);
        return repository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Lote com ID: " + id + " não encontrado."));
    }

    public Lote update(String id, LoteDTO dto) {
        var lote = findById(id);
        Aviario aviario = aviarioRepository.findById(dto.aviarioId()).orElseThrow(() -> new NotFoundException("Aviário com ID: " + dto.aviarioId() + " não encontrado."));
        BeanUtils.copyProperties(dto, lote, "id");
        lote.setAviario(aviario);
        return repository.save(lote);
    }

    public void delete(String id) {
        repository.delete(findById(id));
    }
}
