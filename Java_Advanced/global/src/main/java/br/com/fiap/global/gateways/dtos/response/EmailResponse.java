package br.com.fiap.global.gateways.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailResponse {
    private String message;
    private String email;
    private boolean valid;
}

