package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {

    @NotNull(message = "Id do usuário não pode ser nulo")
    private Integer usuarioId;

    @NotNull(message = "Senha atual não pode ser nula")
    @Size(message = "Senha atual deve ter entre 5 e 30 caracteres", min = 5, max = 30)
    private String novaSenha;
}

