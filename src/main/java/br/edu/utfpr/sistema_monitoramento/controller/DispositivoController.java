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

import br.edu.utfpr.sistema_monitoramento.dtos.DispositivoDTO;
import br.edu.utfpr.sistema_monitoramento.models.Dispositivo;
import br.edu.utfpr.sistema_monitoramento.services.DispositivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dispositivos")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Dispositivo", description = "Endpoints para gerenciar dispositivos")
public class DispositivoController {

    private final DispositivoService service;

    public DispositivoController(DispositivoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar dispositivo", description = "Cria um novo dispositivo.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Dispositivo.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public Dispositivo save(@RequestBody DispositivoDTO dto) {
        return service.save(dto);
    }

    @Operation(summary = "Atualizar dispositivo", description = "Atualiza um dispositivo existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Dispositivo.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public Dispositivo update(@PathVariable String id, @RequestBody DispositivoDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Listar dispositivos", description = "Lista todos os dispositivos com paginação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Dispositivo.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<Dispositivo> findAll(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.findAll(pagina, tamanho);
    }

    @Operation(summary = "Obter dispositivo por ID", description = "Obtém um dispositivo pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Dispositivo.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public Dispositivo findById(@PathVariable String id) {
        return service.findById(id);
    }

    @Operation(summary = "Deletar dispositivo", description = "Deleta um dispositivo pelo seu ID.")
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
