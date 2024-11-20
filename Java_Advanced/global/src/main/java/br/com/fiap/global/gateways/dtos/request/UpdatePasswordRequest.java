package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdatePasswordRequest {

    @NotNull(message = "Id do usuário não pode ser nulo")
    @Schema(description = "Id do Usuário", example = "1")
    private Integer usuarioId;

    @NotNull(message = "Senha atual não pode ser nula")
    @Size(message = "Senha atual deve ter entre 5 e 30 caracteres", min = 5, max = 30)
    @Schema(description = "Nova senha", example = "novaSenha")
    private String novaSenha;
}

