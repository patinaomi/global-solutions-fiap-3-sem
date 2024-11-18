package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

        @NotNull(message = "Email não pode ser nulo")
        private String email;

        @NotNull(message = "Senha não pode ser nula")
        private String senha;

        @NotNull(message = "Id não pode ser nulo")
        private String id;
}
