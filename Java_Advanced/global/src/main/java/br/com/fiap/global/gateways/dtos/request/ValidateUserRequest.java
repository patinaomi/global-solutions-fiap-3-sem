package br.com.fiap.global.gateways.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ValidateUserRequest {
    private String email;
    private LocalDate dataNasc;
}

