package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class ValidateUserRequest {

    @NotNull(message = "Nome não pode ser nulo")
    @Schema(description = "Novo E-mail", example = "pati@gmail.com")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento não pode ser nula")
    @Schema(description = "Data de Nascimento", example = "1994-07-24")
    private LocalDate dataNasc;
}

