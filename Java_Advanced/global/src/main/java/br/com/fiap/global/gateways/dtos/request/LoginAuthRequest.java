package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAuthRequest {

    @NotNull(message = "Email não pode ser nulo")
    private String email;

    @NotNull(message = "Senha não pode ser nula")
    private String senha;
}
