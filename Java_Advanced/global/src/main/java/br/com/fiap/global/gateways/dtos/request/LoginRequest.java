package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class LoginRequest {

    @NotNull(message = "Email não pode ser nulo")
    @Email(message = "Email inválido")
    @Schema(description = "E-Mail do Usuário", example = "claudio@gmail.com")
    private String email;

    @NotNull(message = "Senha não pode ser nula")
    @Schema(description = "Senha do Usuário", example = "123456")
    private String senha;
}
