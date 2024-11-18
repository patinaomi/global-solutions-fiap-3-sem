package br.com.fiap.global.gateways.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginAuthResponse {
    private String message;
    private Integer id;
}
