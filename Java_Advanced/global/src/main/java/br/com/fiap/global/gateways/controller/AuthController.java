package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.LoginRequest;
import br.com.fiap.global.gateways.dtos.request.ValidateEmailRequest;
import br.com.fiap.global.gateways.dtos.request.ValidateUserRequest;
import br.com.fiap.global.gateways.dtos.response.EmailErrorResponse;
import br.com.fiap.global.gateways.dtos.response.EmailResponse;
import br.com.fiap.global.gateways.dtos.response.ErrorResponse;
import br.com.fiap.global.gateways.dtos.response.LoginAuthResponse;
import br.com.fiap.global.gateways.dtos.response.MessageResponse;
import br.com.fiap.global.gateways.dtos.response.ValidateUserResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
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
            return ResponseEntity.ok(new ValidateUserResponse("Usuário validado com sucesso", usuario.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Usuário não encontrado ou dados incorretos"));
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
