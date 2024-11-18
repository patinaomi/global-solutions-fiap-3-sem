package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Login;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.dtos.request.LoginRequest;
import br.com.fiap.global.gateways.dtos.response.LoginResponse;
import br.com.fiap.global.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "Realizar Login", description = "Autentica o usuário e registra o login no sistema")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Login login = loginService.register(request.getEmail(), request.getSenha());

        LoginResponse response = LoginResponse.builder()
                .id(login.getId())
                .dataHora(login.getDataHora())
                .usuarioId(login.getUsuario().getId())
                .build();

        return ResponseEntity.ok(response);
    }

}
