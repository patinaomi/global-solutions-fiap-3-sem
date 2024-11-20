package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.ItemCasa;
import br.com.fiap.global.gateways.dtos.request.ItemCasaRequest;
import br.com.fiap.global.gateways.dtos.response.ItemCasaResponse;
import br.com.fiap.global.service.ItemCasaService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/itenscasa", produces = "application/json")
@Tag(name = "itemcasa", description = "Operações relacionadas aos itens da casa")
public class ItemCasaController {

    private final ItemCasaService service;

    @Operation(summary = "Cria um novo item da casa", description = "Cria um novo item da casa com base nos dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item Casa criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCasaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ItemCasaRequest request) {
        try {
            ItemCasa itemCasa = ItemCasa.builder()
                    .descricao(request.getDescricao())
                    .build();

            ItemCasa itemCasaSalvo = service.create(itemCasa);

            ItemCasaResponse response = ItemCasaResponse.builder()
                    .id(itemCasaSalvo.getId())
                    .descricao(itemCasaSalvo.getDescricao())
                    .build();

            Link link = linkTo(ItemCasaController.class).slash(itemCasaSalvo.getId()).withSelfRel();
            response.add(link);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Busca todos Itens da Casa", description = "Busca todos os itens da casa cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens da casa retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCasaResponse[].class))),
            @ApiResponse(responseCode = "404", description = "Nenhum item da casa encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<ItemCasa> itensCasa = service.findAll();

            if (itensCasa.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum item da casa encontrado.");
            }

            List<ItemCasaResponse> responses = itensCasa.stream()
                    .map(item -> ItemCasaResponse.builder()
                            .id(item.getId())
                            .descricao(item.getDescricao())
                            .build())
                    .toList();

            responses.forEach(response -> response.add(
                    linkTo(ItemCasaController.class).slash(response.getId()).withSelfRel()
            ));

            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar item da casa por ID", description = "Retorna um item da casa com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item da casa encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCasaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Item da casa não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            ItemCasa itemCasa = service.findById(id);

            ItemCasaResponse response = ItemCasaResponse.builder()
                    .id(itemCasa.getId())
                    .descricao(itemCasa.getDescricao())
                    .build();

            Link link = linkTo(ItemCasaController.class).slash(itemCasa.getId()).withSelfRel();
            response.add(link);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item da casa com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza um item da casa", description = "Atualiza um item da casa com base nos dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item da casa atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCasaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Item da casa não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ItemCasaRequest request) {
        try {
            ItemCasa itemCasa = ItemCasa.builder()
                    .id(id)
                    .descricao(request.getDescricao())
                    .build();

            ItemCasa itemCasaAtualizado = service.update(id, itemCasa);

            ItemCasaResponse response = ItemCasaResponse.builder()
                    .id(itemCasaAtualizado.getId())
                    .descricao(itemCasaAtualizado.getDescricao())
                    .build();

            Link link = linkTo(ItemCasaController.class).slash(itemCasaAtualizado.getId()).withSelfRel();
            response.add(link);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item da casa com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Deleta um item da casa", description = "Deleta um item da casa com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item da casa deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item da casa não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Item da casa com ID " + id + " foi deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item da casa com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
