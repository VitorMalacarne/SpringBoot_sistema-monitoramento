package br.edu.utfpr.sistema_monitoramento.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PessoaDTO(
        @NotBlank(message = "Não pode ser vazio")
        @Size(max = 255)
        String name,
        @NotNull(message = "Não pode ser vazio")
        @Size(max = 255)
        String email) {

}
