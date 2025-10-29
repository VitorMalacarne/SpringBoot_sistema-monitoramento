package br.edu.utfpr.sistema_monitoramento.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtuadorDTO(@NotNull(message = "Não pode ser nulo")
        @Size(max = 255)
        String tipo,
        @NotBlank(message = "Não pode ser vazio")
        String status,
        @NotBlank(message = "Não pode ser vazio")
        UUID dispositivoId) {

}
