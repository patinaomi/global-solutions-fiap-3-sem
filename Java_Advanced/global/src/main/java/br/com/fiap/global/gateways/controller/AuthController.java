package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.LoginAuthRequest;
import br.com.fiap.global.gateways.dtos.request.UpdatePasswordRequest;
import br.com.fiap.global.gateways.dtos.request.ValidateEmailRequest;
import br.com.fiap.global.gateways.dtos.request.ValidateUserRequest;
import br.com.fiap.global.gateways.dtos.response.EmailErrorResponse;
import br.com.fiap.global.gateways.dtos.response.EmailResponse;
import br.com.fiap.global.gateways.dtos.response.ErrorResponse;
import br.com.fiap.global.gateways.dtos.response.LoginAuthResponse;
import br.com.fiap.global.gateways.dtos.response.MessageResponse;
import br.com.fiap.global.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAuthRequest request) {
        Usuario usuario = authenticationService.authenticate(request.getEmail(), request.getSenha());
        if (usuario != null) {
            return ResponseEntity.ok(new LoginAuthResponse("Login bem-sucedido", usuario.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Credenciais inválidas"));
        }
    }

    @PostMapping("/validate-user")
    public ResponseEntity<?> validateUser(@RequestBody ValidateUserRequest request) {
        Usuario usuario = authenticationService.findByEmailAndDateOfBirth(request.getEmail(), request.getDataNasc());
        if (usuario != null) {
            return ResponseEntity.ok(new MessageResponse("Usuário validado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Usuário não encontrado ou dados incorretos"));
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
        boolean isUpdated = authenticationService.updatePassword(request.getUsuarioId(), request.getNovaSenha());

        if (isUpdated) {
            return ResponseEntity.ok(new MessageResponse("Senha atualizada com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Erro ao atualizar senha"));
        }
    }


    @PostMapping("/validate-email")
    public ResponseEntity<?> validateEmail(@RequestBody ValidateEmailRequest request) {
        Usuario usuario = authenticationService.findByEmail(request.getEmail());

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

