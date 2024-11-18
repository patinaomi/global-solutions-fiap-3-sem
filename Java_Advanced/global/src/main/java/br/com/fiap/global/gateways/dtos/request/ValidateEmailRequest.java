package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateEmailRequest {

    @NotNull(message = "Email não pode ser nulo")
    private String email;
}

