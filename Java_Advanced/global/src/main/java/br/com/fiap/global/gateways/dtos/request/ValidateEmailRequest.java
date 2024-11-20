package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateEmailRequest {

    @NotNull(message = "Email não pode ser nulo")
    @Schema(description = "E-mail para Validar", example = "pati@gmail.com")
    private String email;
}

