package br.com.fiap.global.gateways.controller;

import br.com.fiap.global.domains.Login;
import br.com.fiap.global.gateways.dtos.request.LoginRequest;
import br.com.fiap.global.gateways.dtos.response.LoginResponse;
import br.com.fiap.global.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Login", description = "Operações relacionadas ao login do usuário")
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "Realizar Login", description = "Autentica o usuário e registra o login no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Login login = loginService.register(request.getEmail(), request.getSenha());

        LoginResponse response = LoginResponse.builder()
                .id(login.getId())
                .dataHora(login.getDataHora())
                .usuarioId(login.getUsuario().getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
