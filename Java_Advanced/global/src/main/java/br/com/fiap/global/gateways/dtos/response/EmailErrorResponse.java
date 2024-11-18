package br.com.fiap.global.gateways.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailErrorResponse {
    private String message;
    private boolean valid;
}

