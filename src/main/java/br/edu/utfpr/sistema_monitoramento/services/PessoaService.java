package br.edu.utfpr.sistema_monitoramento.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.edu.utfpr.sistema_monitoramento.dtos.PessoaDTO;
import br.edu.utfpr.sistema_monitoramento.exception.NotFoundException;
import br.edu.utfpr.sistema_monitoramento.models.Pessoa;
import br.edu.utfpr.sistema_monitoramento.repositories.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa save(PessoaDTO dto) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);
        return repository.save(pessoa);
    }

    public Page<Pessoa> findAll(int pagina, int tamanho) {
        return repository.findAll(PageRequest.of(pagina, tamanho));
    }

    public Pessoa findById(String id) {
        UUID uuid = UUID.fromString(id);
        return repository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada com ID: " + id));
    }

    public Pessoa update(String id, PessoaDTO dto) {
        var pessoaExistente = findById(id);
        BeanUtils.copyProperties(dto, pessoaExistente, "id");
        return repository.save(pessoaExistente);
    }

    public void delete(String id) {
        repository.delete(findById(id));
    }
}
