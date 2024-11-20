package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Comodo;
import br.com.fiap.global.gateways.dtos.request.ComodoRequest;
import br.com.fiap.global.gateways.dtos.response.ComodoResponse;
import br.com.fiap.global.service.ComodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/comodos", produces = "application/json")
@Tag(name = "comodo", description = "Operações relacionadas aos cômodos")
public class ComodoController {

    private final ComodoService service;

    @Operation(summary = "Cria um novo comodo", description = "Cria um novo cômodo com base nos dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cômodo criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComodoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ComodoRequest request) {
        Comodo comodo = Comodo.builder()
                .descricao(request.getDescricao())
                .build();

        Comodo comodoSalvo = service.create(comodo);

        ComodoResponse response = ComodoResponse.builder()
                .id(comodoSalvo.getId())
                .descricao(comodoSalvo.getDescricao())
                .build();

        Link link = linkTo(ComodoController.class).slash(comodoSalvo.getId()).withSelfRel();
        response.add(link);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Operation(summary = "Busca todos os Cômodos", description = "Busca todos os cômodos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cômodos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComodoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum cômodo encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Comodo> comodos = service.findAll();

        if (comodos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cômodo encontrado.");
        }

        List<ComodoResponse> responses = comodos.stream().map(comodo -> {
            ComodoResponse response = ComodoResponse.builder()
                    .id(comodo.getId())
                    .descricao(comodo.getDescricao())
                    .build();
            Link link = linkTo(methodOn(ComodoController.class).findAll()).withSelfRel();
            response.add(link);
            return response;
        }).toList();

        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Buscar cômodo por ID", description = "Retorna um cômodo com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cômodo encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComodoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cômodo não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Comodo comodo = service.findById(id);

        ComodoResponse response = ComodoResponse.builder()
                .id(comodo.getId())
                .descricao(comodo.getDescricao())
                .build();

        response.add(linkTo(methodOn(ComodoController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar cômodo", description = "Atualiza um cômodo com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cômodo atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComodoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cômodo não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ComodoRequest request) {
        Comodo comodo = Comodo.builder()
                .id(id)
                .descricao(request.getDescricao())
                .build();

        Comodo comodoSalvo = service.update(id, comodo);

        ComodoResponse response = ComodoResponse.builder()
                .id(comodoSalvo.getId())
                .descricao(comodoSalvo.getDescricao())
                .build();

        response.add(linkTo(methodOn(ComodoController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Cômodo", description = "Deleta um cômodo com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cômodo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cômodo não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito de integridade"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Cômodo com ID " + id + " deletado com sucesso.");
    }
}
