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

import br.edu.utfpr.sistema_monitoramento.dtos.SensorDTO;
import br.edu.utfpr.sistema_monitoramento.models.Sensor;
import br.edu.utfpr.sistema_monitoramento.services.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/sensores")
@Tag(name = "Sensor", description = "Endpoints para gerenciar sensores")
@SecurityRequirement(name = "Authorization")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @Operation(summary = "Criar sensor", description = "Cria um novo sensor.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Sensor.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public Sensor save(@RequestBody SensorDTO dto) {
        return service.save(dto);
    }

    @Operation(summary = "Atualizar sensor", description = "Atualiza um sensor existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Sensor.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public Sensor update(@PathVariable String id, @RequestBody SensorDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Listar sensores", description = "Lista todos os sensores com paginação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Sensor.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<Sensor> findAll(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.findAll(pagina, tamanho);
    }

    @Operation(summary = "Obter sensor por ID", description = "Obtém um sensor pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Sensor.class), mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public Sensor findById(@PathVariable String id) {
        return service.findById(id);
    }

    @Operation(summary = "Deletar sensor", description = "Deleta um sensor pelo seu ID.")
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