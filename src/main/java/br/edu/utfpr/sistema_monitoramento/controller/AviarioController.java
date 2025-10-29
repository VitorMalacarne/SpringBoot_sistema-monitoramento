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

import br.edu.utfpr.sistema_monitoramento.dtos.AviarioDTO;
import br.edu.utfpr.sistema_monitoramento.models.Aviario;
import br.edu.utfpr.sistema_monitoramento.services.AviarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aviarios")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Aviário", description = "Endpoints para gerenciar aviários")
public class AviarioController {

    private final AviarioService service;

    public AviarioController(AviarioService service) {
        this.service = service;
    }

    @Operation(summary = "Criar aviário", description = "Cria um novo aviário.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Aviario.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public Aviario save(@Valid @RequestBody AviarioDTO dto) {
        return service.save(dto);
    }

    @Operation(summary = "Atualizar aviário", description = "Atualiza um aviário existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Aviario.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public Aviario update(@PathVariable String id, @RequestBody AviarioDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Listar aviários", description = "Lista todos os aviários com paginação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Aviario.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<Aviario> findAll(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.findAll(pagina, tamanho);
    }

    @Operation(summary = "Obter aviário por ID", description = "Obtém um aviário pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Aviario.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public Aviario findById(@PathVariable String id) {
        return service.findById(id);
    }

    @Operation(summary = "Deletar aviário", description = "Deleta um aviário pelo seu ID.")
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
