package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Endereco;
import br.com.fiap.global.domains.Estado;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.UpdatePasswordRequest;
import br.com.fiap.global.gateways.dtos.request.UsuarioRequest;
import br.com.fiap.global.gateways.dtos.response.EnderecoResponse;
import br.com.fiap.global.gateways.dtos.response.ErrorResponse;
import br.com.fiap.global.gateways.dtos.response.MessageResponse;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
@Tag(name = "usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EstadoService estadoService;

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário com base nos dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioRequest request) {
        try {
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

            UsuarioResponse response = mapToUsuarioResponse(usuarioSalvo);

            Link link = linkTo(UsuarioController.class).slash(usuarioSalvo.getId()).withSelfRel();
            response.add(link);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar todos os usuários", description = "Retorna uma lista de todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse[].class))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Usuario> usuarios = usuarioService.findAll();

            if (usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado.");
            }

            List<UsuarioResponse> responses = usuarios.stream()
                    .map(this::mapToUsuarioResponse)
                    .toList();

            responses.forEach(response -> response.add(
                    linkTo(UsuarioController.class).slash(response.getId()).withSelfRel()
            ));

            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.findById(id);

            UsuarioResponse response = mapToUsuarioResponse(usuario);
            response.add(linkTo(UsuarioController.class).slash(id).withSelfRel());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um usuário", description = "Atualiza um usuário existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody UsuarioRequest request) {
        try {
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
                    .id(id)
                    .nome(request.getNome())
                    .sobrenome(request.getSobrenome())
                    .telefone(request.getTelefone())
                    .email(request.getEmail())
                    .senha(request.getSenha())
                    .dataNasc(request.getDataNasc())
                    .endereco(endereco)
                    .build();

            Usuario usuarioAtualizado = usuarioService.update(id, usuario);

            UsuarioResponse response = mapToUsuarioResponse(usuarioAtualizado);
            response.add(linkTo(UsuarioController.class).slash(id).withSelfRel());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar parcialmente um usuário", description = "Atualiza campos específicos de um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartial(@PathVariable Integer id, @RequestBody UsuarioRequest request) {
        try {
            Usuario usuarioExistente = usuarioService.findById(id);

            if (request.getNome() != null) usuarioExistente.setNome(request.getNome());
            if (request.getSobrenome() != null) usuarioExistente.setSobrenome(request.getSobrenome());
            if (request.getTelefone() != null) usuarioExistente.setTelefone(request.getTelefone());
            if (request.getEmail() != null) usuarioExistente.setEmail(request.getEmail());
            if (request.getSenha() != null) usuarioExistente.setSenha(request.getSenha());
            if (request.getDataNasc() != null) usuarioExistente.setDataNasc(request.getDataNasc());
            if (request.getEndereco() != null) {
                Estado estado = estadoService.findById(request.getEndereco().getEstadoId());
                usuarioExistente.getEndereco().setLogradouro(request.getEndereco().getLogradouro());
                usuarioExistente.getEndereco().setNumero(request.getEndereco().getNumero());
                usuarioExistente.getEndereco().setComplemento(request.getEndereco().getComplemento());
                usuarioExistente.getEndereco().setBairro(request.getEndereco().getBairro());
                usuarioExistente.getEndereco().setCidade(request.getEndereco().getCidade());
                usuarioExistente.getEndereco().setCep(request.getEndereco().getCep());
                usuarioExistente.getEndereco().setEstado(estado);
            }

            Usuario usuarioAtualizado = usuarioService.update(id, usuarioExistente);

            UsuarioResponse response = mapToUsuarioResponse(usuarioAtualizado);
            response.add(linkTo(UsuarioController.class).slash(id).withSelfRel());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Deletar um usuário", description = "Remove um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário com ID " + id + " deletado.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar senha", description = "Atualiza a senha de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar senha",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        boolean isUpdated = usuarioService.updatePassword(request.getId(), request.getNovaSenha());
        if (isUpdated) {
            return ResponseEntity.ok(new MessageResponse("Senha atualizada com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Erro ao atualizar senha"));
        }
    }

    private UsuarioResponse mapToUsuarioResponse(Usuario usuario) {
        return UsuarioResponse.builder()
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
    }
}
