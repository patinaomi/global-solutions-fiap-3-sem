package br.com.fiap.global.gateways.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LoginResponse {
    private Integer id;
    private LocalDateTime dataHora;
    private Integer usuarioId;
}

