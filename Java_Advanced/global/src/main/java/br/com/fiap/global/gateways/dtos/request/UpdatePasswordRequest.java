package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {

    @NotNull(message = "Id do usuário não pode ser nulo")
    private Integer usuarioId;

    @NotNull(message = "Senha atual não pode ser nula")
    private String novaSenha;
}

