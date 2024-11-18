package br.com.fiap.global.gateways.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LoginResponse extends RepresentationModel<LoginResponse> {
    private Integer id;
    private LocalDateTime dataHora;
    private Integer usuarioId;
}

