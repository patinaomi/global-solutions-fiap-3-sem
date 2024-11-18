package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class ValidateUserRequest {

    @NotNull(message = "Nome não pode ser nulo")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento não pode ser nula")
    private LocalDate dataNasc;
}

