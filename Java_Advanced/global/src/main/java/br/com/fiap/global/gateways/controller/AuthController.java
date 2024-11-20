package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.*;
import br.com.fiap.global.gateways.dtos.response.*;
import br.com.fiap.global.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints relacionados à autenticação e validação de usuários")
public class AuthController {

    private final AuthenticationService service;

    @Operation(summary = "Login de usuário", description = "Autentica o usuário com base no e-mail e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginAuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAuthRequest request) {
        Usuario usuario = service.authenticate(request.getEmail(), request.getSenha());
        if (usuario != null) {
            return ResponseEntity.ok(new LoginAuthResponse("Login bem-sucedido", usuario.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Credenciais inválidas"));
        }
    }

    @Operation(summary = "Validar usuário", description = "Valida o usuário com base no e-mail e data de nascimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário validado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou dados incorretos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/validate-user")
    public ResponseEntity<?> validateUser(@Valid @RequestBody ValidateUserRequest request) {
        Usuario usuario = service.findByEmailAndDateOfBirth(request.getEmail(), request.getDataNasc());
        if (usuario != null) {
            return ResponseEntity.ok(new MessageResponse("Usuário validado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Usuário não encontrado ou dados incorretos"));
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
        boolean isUpdated = service.updatePassword(request.getUsuarioId(), request.getNovaSenha());

        if (isUpdated) {
            return ResponseEntity.ok(new MessageResponse("Senha atualizada com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Erro ao atualizar senha"));
        }
    }

    @Operation(summary = "Validar e-mail", description = "Valida a existência de um e-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "E-mail encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmailResponse.class))),
            @ApiResponse(responseCode = "404", description = "E-mail não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmailErrorResponse.class)))
    })
    @PostMapping("/validate-email")
    public ResponseEntity<?> validateEmail(@Valid @RequestBody ValidateEmailRequest request) {
        Usuario usuario = service.findByEmail(request.getEmail());

        if (usuario != null) {
            boolean isValid = usuario.getEmail().equals(request.getEmail());
            return ResponseEntity.ok(new EmailResponse("E-mail encontrado", usuario.getEmail(), isValid));
        } else {
            boolean isValid = false;
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EmailErrorResponse("E-mail não encontrado", isValid));
        }
    }
}
