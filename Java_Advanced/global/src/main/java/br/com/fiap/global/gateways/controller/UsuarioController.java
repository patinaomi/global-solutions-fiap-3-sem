package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Endereco;
import br.com.fiap.global.domains.Estado;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.UsuarioRequest;
import br.com.fiap.global.gateways.dtos.request.UsuarioUpdateRequest;
import br.com.fiap.global.gateways.dtos.response.EnderecoResponse;
import br.com.fiap.global.gateways.dtos.response.UsuarioResponse;
import br.com.fiap.global.service.EstadoService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
@Tag(name = "usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EstadoService estadoService;

    @Operation(summary = "Cria um novo usuario", description = "Cria um novo usuario com base nos dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioRequest request) {

        Estado estado = estadoService.findById(request.getEndereco().getEstadoId());

        Endereco endereco = Endereco.builder()
                .logradouro(request.getEndereco().getLogradouro())
                .numero(request.getEndereco().getNumero())
                .complemento(request.getEndereco().getComplemento())
                .bairro(request.getEndereco().getBairro())
                .cidade(request.getEndereco().getCidade())
                .cep(request.getEndereco().getCep())
                .estado(estado)
                .build();

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .telefone(request.getTelefone())
                .email(request.getEmail())
                .senha(request.getSenha())
                .dataNasc(request.getDataNasc())
                .endereco(endereco)
                .build();

        Usuario usuarioSalvo = usuarioService.create(usuario);

        UsuarioResponse response = UsuarioResponse.builder()
                .id(usuarioSalvo.getId())
                .nome(usuarioSalvo.getNome())
                .sobrenome(usuarioSalvo.getSobrenome())
                .telefone(usuarioSalvo.getTelefone())
                .email(usuarioSalvo.getEmail())
                .dataNasc(request.getDataNasc())
                .endereco(
                        EnderecoResponse.builder()
                                .logradouro(usuarioSalvo.getEndereco().getLogradouro())
                                .numero(usuarioSalvo.getEndereco().getNumero())
                                .complemento(usuarioSalvo.getEndereco().getComplemento())
                                .bairro(usuarioSalvo.getEndereco().getBairro())
                                .cidade(usuarioSalvo.getEndereco().getCidade())
                                .cep(usuarioSalvo.getEndereco().getCep())
                                .estadoId(usuarioSalvo.getEndereco().getEstado().getId())
                                .build()
                )
                .build();

        Link link = linkTo(UsuarioController.class).slash(usuarioSalvo.getId()).withSelfRel();
        response.add(link);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Buscar todos os Usuários", description = "Retorna uma lista de todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();

        List<UsuarioResponse> usuarioResponses = usuarios.stream().map(usuario -> {
            UsuarioResponse response = UsuarioResponse.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .sobrenome(usuario.getSobrenome())
                    .telefone(usuario.getTelefone())
                    .email(usuario.getEmail())
                    .dataNasc(usuario.getDataNasc())
                    .endereco(
                            EnderecoResponse.builder()
                                    .logradouro(usuario.getEndereco().getLogradouro())
                                    .numero(usuario.getEndereco().getNumero())
                                    .complemento(usuario.getEndereco().getComplemento())
                                    .bairro(usuario.getEndereco().getBairro())
                                    .cidade(usuario.getEndereco().getCidade())
                                    .cep(usuario.getEndereco().getCep())
                                    .estadoId(usuario.getEndereco().getEstado().getId())
                                    .build()
                    )
                    .build();
            Link link = linkTo(methodOn(UsuarioController.class).findAll()).withSelfRel();
            response.add(link);
            return response;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(usuarioResponses);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);

        UsuarioResponse response = UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .dataNasc(usuario.getDataNasc())
                .endereco(
                        EnderecoResponse.builder()
                                .logradouro(usuario.getEndereco().getLogradouro())
                                .numero(usuario.getEndereco().getNumero())
                                .complemento(usuario.getEndereco().getComplemento())
                                .bairro(usuario.getEndereco().getBairro())
                                .cidade(usuario.getEndereco().getCidade())
                                .cep(usuario.getEndereco().getCep())
                                .estadoId(usuario.getEndereco().getEstado().getId())
                                .build()
                )
                .build();

        response.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody UsuarioRequest request) {
        Estado estado = estadoService.findById(request.getEndereco().getEstadoId());

        Endereco endereco = Endereco.builder()
                .logradouro(request.getEndereco().getLogradouro())
                .numero(request.getEndereco().getNumero())
                .complemento(request.getEndereco().getComplemento())
                .bairro(request.getEndereco().getBairro())
                .cidade(request.getEndereco().getCidade())
                .cep(request.getEndereco().getCep())
                .estado(estado)
                .build();

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .telefone(request.getTelefone())
                .email(request.getEmail())
                .senha(request.getSenha())
                .dataNasc(request.getDataNasc())
                .endereco(endereco)
                .build();

        Usuario usuarioSalvo = usuarioService.update(id, usuario);

        UsuarioResponse response = UsuarioResponse.builder()
                .id(usuarioSalvo.getId())
                .nome(usuarioSalvo.getNome())
                .sobrenome(usuarioSalvo.getSobrenome())
                .telefone(usuarioSalvo.getTelefone())
                .email(usuarioSalvo.getEmail())
                .dataNasc(usuarioSalvo.getDataNasc())
                .endereco(
                        EnderecoResponse.builder()
                                .logradouro(usuarioSalvo.getEndereco().getLogradouro())
                                .numero(usuarioSalvo.getEndereco().getNumero())
                                .complemento(usuarioSalvo.getEndereco().getComplemento())
                                .bairro(usuarioSalvo.getEndereco().getBairro())
                                .cidade(usuarioSalvo.getEndereco().getCidade())
                                .cep(usuarioSalvo.getEndereco().getCep())
                                .estadoId(usuarioSalvo.getEndereco().getEstado().getId())
                                .build()
                )
                .build();

        Link link = linkTo(UsuarioController.class).slash(usuarioSalvo.getId()).withSelfRel();
        response.add(link);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuário", description = "Deleta um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito de integridade"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.ok("Usuário com ID " + id + " deletado com sucesso");
    }

    @Operation(summary = "Atualizar campos específicos do usuário", description = "Atualiza campos específicos de um usuário com base no ID fornecido")
    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> updateParcial(@PathVariable Integer id, @RequestBody UsuarioUpdateRequest request) {
        Usuario usuario = usuarioService.findById(id);

        if (request.getNome() != null) {
            usuario.setNome(request.getNome());
        }

        if (request.getSobrenome() != null) {
            usuario.setSobrenome(request.getSobrenome());
        }

        if (request.getTelefone() != null) {
            usuario.setTelefone(request.getTelefone());
        }

        if (request.getEmail() != null) {
            usuario.setEmail(request.getEmail());
        }

        if (request.getSenha() != null) {
            usuario.setSenha(request.getSenha());
        }

        if(request.getDataNasc() != null) {
            usuario.setDataNasc(request.getDataNasc());
        }

        if (request.getEndereco() != null) {
            Estado estado = estadoService.findById(request.getEndereco().getEstadoId());

            Endereco enderecoExistente = usuario.getEndereco();

            if (enderecoExistente == null) {
                Endereco novoEndereco = Endereco.builder()
                        .logradouro(request.getEndereco().getLogradouro())
                        .numero(request.getEndereco().getNumero())
                        .complemento(request.getEndereco().getComplemento())
                        .bairro(request.getEndereco().getBairro())
                        .cidade(request.getEndereco().getCidade())
                        .cep(request.getEndereco().getCep())
                        .estado(estado)
                        .build();

                usuario.setEndereco(novoEndereco);
            } else {
                if (request.getEndereco().getLogradouro() != null) {
                    enderecoExistente.setLogradouro(request.getEndereco().getLogradouro());
                }
                if (request.getEndereco().getNumero() != null) {
                    enderecoExistente.setNumero(request.getEndereco().getNumero());
                }
                if (request.getEndereco().getComplemento() != null) {
                    enderecoExistente.setComplemento(request.getEndereco().getComplemento());
                }
                if (request.getEndereco().getBairro() != null) {
                    enderecoExistente.setBairro(request.getEndereco().getBairro());
                }
                if (request.getEndereco().getCidade() != null) {
                    enderecoExistente.setCidade(request.getEndereco().getCidade());
                }
                if (request.getEndereco().getCep() != null) {
                    enderecoExistente.setCep(request.getEndereco().getCep());
                }
                if (request.getEndereco().getEstadoId() != null) {
                    enderecoExistente.setEstado(estado);
                }
            }
        }

        Usuario usuarioSalvo = usuarioService.update(id, usuario);

        UsuarioResponse response = UsuarioResponse.builder()
                .id(usuarioSalvo.getId())
                .nome(usuarioSalvo.getNome())
                .sobrenome(usuarioSalvo.getSobrenome())
                .telefone(usuarioSalvo.getTelefone())
                .email(usuarioSalvo.getEmail())
                .dataNasc(usuarioSalvo.getDataNasc())
                .endereco(
                        EnderecoResponse.builder()
                                .logradouro(usuarioSalvo.getEndereco().getLogradouro())
                                .numero(usuarioSalvo.getEndereco().getNumero())
                                .complemento(usuarioSalvo.getEndereco().getComplemento())
                                .bairro(usuarioSalvo.getEndereco().getBairro())
                                .cidade(usuarioSalvo.getEndereco().getCidade())
                                .cep(usuarioSalvo.getEndereco().getCep())
                                .estadoId(usuarioSalvo.getEndereco().getEstado().getId())
                                .build()
                )
                .build();

        Link link = linkTo(UsuarioController.class).slash(usuarioSalvo.getId()).withSelfRel();
        response.add(link);

        return ResponseEntity.ok(response);
    }
}
