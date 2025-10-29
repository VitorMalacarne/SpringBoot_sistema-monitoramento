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

import br.edu.utfpr.sistema_monitoramento.dtos.LoteDTO;
import br.edu.utfpr.sistema_monitoramento.models.Lote;
import br.edu.utfpr.sistema_monitoramento.services.LoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/lotes")
@Tag(name = "Lote", description = "Endpoints para gerenciar lotes")
@SecurityRequirement(name = "Authorization")
public class LoteController {

    private final LoteService service;

    public LoteController(LoteService service) {
        this.service = service;
    }

    @Operation(summary = "Criar lote", description = "Cria um novo lote.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Lote.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public Lote save(@RequestBody LoteDTO dto) {
        return service.save(dto);
    }

    @Operation(summary = "Atualizar lote", description = "Atualiza um lote existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Lote.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public Lote update(@PathVariable String id, @RequestBody LoteDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Listar lotes", description = "Lista todos os lotes com paginação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Lote.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<Lote> findAll(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.findAll(pagina, tamanho);
    }

    @Operation(summary = "Obter lote por ID", description = "Obtém um lote pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Lote.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public Lote findById(@PathVariable String id) {
        return service.findById(id);
    }

    @Operation(summary = "Deletar lote", description = "Deleta um lote pelo seu ID.")
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
