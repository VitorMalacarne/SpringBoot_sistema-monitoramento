package br.edu.utfpr.sistema_monitoramento.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DispositivoDTO(
        @NotNull(message = "Não pode ser nulo")
        @Size(max = 255)
        String serial,
        @NotBlank(message = "Não pode ser vazio")
        String Status,
        @NotBlank(message = "Não pode ser vazio")
        UUID aviarioId) {

}
