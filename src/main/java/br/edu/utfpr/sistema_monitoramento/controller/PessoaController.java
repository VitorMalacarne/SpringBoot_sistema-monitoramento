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

import br.edu.utfpr.sistema_monitoramento.dtos.PessoaDTO;
import br.edu.utfpr.sistema_monitoramento.models.Pessoa;
import br.edu.utfpr.sistema_monitoramento.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoa", description = "Endpoints para gerenciar pessoas")
@SecurityRequirement(name = "Authorization")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    /**
     * Cria uma nova pessoa.
     */
    @Operation(summary = "Criar pessoa", description = "Cria uma nova pessoa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Pessoa.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public Pessoa save(@Valid @RequestBody PessoaDTO dto) {
        return service.save(dto);
    }

    /**
     * Atualiza uma pessoa existente.
     */
    @Operation(summary = "Atualizar pessoa", description = "Atualiza uma pessoa existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Pessoa.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public Pessoa update(@PathVariable String id, @Valid @RequestBody PessoaDTO dto) {
        return service.update(id, dto);
    }

    /**
     * Lista todas as pessoas com paginação.
     */
    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas com paginação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Pessoa.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<Pessoa> findAll(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.findAll(pagina, tamanho);
    }

    /**
     * Obtém uma pessoa pelo seu ID.
     */
    @Operation(summary = "Obter pessoa por ID", description = "Obtém uma pessoa pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Pessoa.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Deleta uma pessoa pelo seu ID.
     */
    @Operation(summary = "Deletar pessoa", description = "Deleta uma pessoa pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
