package br.edu.utfpr.sistema_monitoramento.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.edu.utfpr.sistema_monitoramento.dtos.DispositivoDTO;
import br.edu.utfpr.sistema_monitoramento.exception.NotFoundException;
import br.edu.utfpr.sistema_monitoramento.models.Aviario;
import br.edu.utfpr.sistema_monitoramento.models.Dispositivo;
import br.edu.utfpr.sistema_monitoramento.repositories.AviarioRepository;
import br.edu.utfpr.sistema_monitoramento.repositories.DispositivoRepository;

@Service
public class DispositivoService {

    private final DispositivoRepository repository;

    @Autowired
    private AviarioRepository aviarioRepo;

    public DispositivoService(DispositivoRepository repository) {
        this.repository = repository;
    }

    public Dispositivo save(DispositivoDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        Aviario aviario = aviarioRepo.findById(dto.aviarioId()).orElseThrow(() -> new NotFoundException("Aviário com ID: " + dto.aviarioId() + " não encontrado."));
        BeanUtils.copyProperties(dto, dispositivo);
        dispositivo.setAviario(aviario);
        return repository.save(dispositivo);
    }

    public Page<Dispositivo> findAll(int pagina, int tamanho) {
        return repository.findAll(PageRequest.of(pagina, tamanho));
    }

    public Dispositivo findById(String id) {
        UUID uuid = UUID.fromString(id);
        return repository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Dispositivo com ID: " + id + " não encontrado."));
    }

    public Dispositivo update(String id, DispositivoDTO dto) {
        var dispositivo = findById(id);
        Aviario aviario = aviarioRepo.findById(dto.aviarioId()).orElseThrow(() -> new NotFoundException("Aviário com ID: " + dto.aviarioId() + " não encontrado."));
        BeanUtils.copyProperties(dto, dispositivo, "id");
        dispositivo.setAviario(aviario);
        return repository.save(dispositivo);
    }

    public void delete(String id) {
        repository.delete(findById(id));
    }
}
