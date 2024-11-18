package br.com.fiap.global.gateways.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
    private Integer usuarioId;
    private String novaSenha;
}

