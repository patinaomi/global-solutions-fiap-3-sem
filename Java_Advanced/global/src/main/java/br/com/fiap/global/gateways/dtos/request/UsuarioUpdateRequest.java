package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@Builder
public class UsuarioUpdateRequest extends RepresentationModel<UsuarioUpdateRequest> {

    @Size(message = "O nome de usuário deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Novo Nome", example = "Novo Nome")
    String nome;

    @Size(message = "O sobrenome deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Novo Sobrenome", example = "Novo Sobreome")
    String sobrenome;

    @Size(message = "O telefone deve ter até 15 caracteres", max = 15)
    @Schema(description = "Novo Telefone", example = "(11)91234-5678")
    String telefone;

    @Email(message = "O e-mail está inválido")
    @Schema(description = "Novo E-mail", example = "claudionovo@gmail.com")
    String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "Nova Data", example = "1994-07-24")
    private LocalDate dataNasc;

    @Valid
    private EnderecoRequest endereco;
}
