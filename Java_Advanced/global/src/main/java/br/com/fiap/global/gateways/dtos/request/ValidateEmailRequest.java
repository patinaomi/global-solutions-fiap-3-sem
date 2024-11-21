package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateEmailRequest {

    @NotNull(message = "Email não pode ser nulo")
    @Schema(description = "E-mail para Validar", example = "pati@gmail.com")
    private String email;
}

