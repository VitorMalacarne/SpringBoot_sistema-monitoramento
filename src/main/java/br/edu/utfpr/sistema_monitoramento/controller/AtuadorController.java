package br.edu.utfpr.sistema_monitoramento.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.sistema_monitoramento.dtos.AtuadorDTO;
import br.edu.utfpr.sistema_monitoramento.models.Atuador;
import br.edu.utfpr.sistema_monitoramento.services.AtuadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/atuadores")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Atuador", description = "Endpoints para gerenciar atuadores")
public class AtuadorController {

    private final AtuadorService service;

    public AtuadorController(AtuadorService service) {
        this.service = service;
    }

    @Operation(summary = "Criar atuador", description = "Cria um novo atuador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Atuador.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public Atuador save(@RequestBody AtuadorDTO dto) {
        return service.save(dto);
    }

    @Operation(summary = "Atualizar atuador", description = "Atualiza um atuador existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Atuador.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public Atuador update(@PathVariable String id, @RequestBody AtuadorDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Listar atuadores", description = "Lista todos os atuadores com paginação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Atuador.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<Atuador> findAll(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.findAll(pagina, tamanho);
    }

    @Operation(summary = "Obter atuador por ID", description = "Obtém um atuador pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Atuador.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public Atuador findById(@PathVariable String id) {
        return service.findById(id);
    }

    @Operation(summary = "Deletar atuador", description = "Deleta um atuador pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}