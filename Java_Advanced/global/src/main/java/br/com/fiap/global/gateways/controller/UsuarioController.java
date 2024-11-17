package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Endereco;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.UsuarioRequest;
import br.com.fiap.global.gateways.dtos.response.EnderecoResponse;
import br.com.fiap.global.gateways.dtos.response.UsuarioResponse;
import br.com.fiap.global.service.UsuarioService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
@Tag(name = "usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Cria um novo usuario", description = "Cria um novo usuario com base nos dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dentista criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioRequest request) {
        Endereco endereco = Endereco.builder()
                .logradouro(request.getEndereco().getLogradouro())
                .numero(request.getEndereco().getNumero())
                .complemento(request.getEndereco().getComplemento())
                .bairro(request.getEndereco().getBairro())
                .cidade(request.getEndereco().getCidade())
                .cep(request.getEndereco().getCep())
                .estadoId(request.getEndereco().getEstadoId())
                .build();

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .telefone(request.getTelefone())
                .email(request.getEmail())
                .senha(request.getSenha())
                .endereco(endereco)
                .build();

        Usuario usuarioSalvo = service.create(usuario);

        UsuarioResponse response = UsuarioResponse.builder()
                .id(usuarioSalvo.getId())
                .nome(usuarioSalvo.getNome())
                .sobrenome(usuarioSalvo.getSobrenome())
                .telefone(usuarioSalvo.getTelefone())
                .email(usuarioSalvo.getEmail())
                .endereco(EnderecoResponse.builder()
                        .logradouro(usuarioSalvo.getEndereco().getLogradouro())
                        .numero(usuarioSalvo.getEndereco().getNumero())
                        .complemento(usuarioSalvo.getEndereco().getComplemento())
                        .bairro(usuarioSalvo.getEndereco().getBairro())
                        .cidade(usuarioSalvo.getEndereco().getCidade())
                        .cep(usuarioSalvo.getEndereco().getCep())
                        .estado(usuarioSalvo.getEndereco().getEstado().getNome())
                        .build())
                .build();

        Link link = linkTo(UsuarioController.class).slash(usuarioSalvo.getId()).withSelfRel();
        response.add(link);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
