package br.edu.utfpr.sistema_monitoramento.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoteDTO(
        @NotBlank(message = "Não pode ser vazio")
        @Size(max = 255)
        String descricao,
        @NotNull(message = "Não pode ser vazio")
        UUID aviarioId) {

}
