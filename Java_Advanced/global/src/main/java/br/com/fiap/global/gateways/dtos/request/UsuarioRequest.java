package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class UsuarioRequest extends RepresentationModel<UsuarioRequest> {

    @NotNull(message = "O nome de usuário não pode ser nulo")
    @Size(message = "O nome de usuário deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Nome do Usuário", example = "Claudio")
    String nome;

    @NotNull(message = "O sobrenome não pode ficar nulo")
    @Size(message = "O sobrenome deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Sobrenome do Usuário", example = "Bispo")
    String sobrenome;

    @Size(message = "O telefone deve ter até 15 caracteres", max = 15)
    @Schema(description = "Telefone do Usuário", example = "(11)91234-5678")
    String telefone;

    @NotNull(message = "O e-mail não pode ser nulo")
    @Email(message = "O e-mail está inválido")
    @Schema(description = "E-mail do Usuário", example = "claudio@gmail.com")
    String email;

    @NotNull(message = "A senha não pode ficar nula")
    @Size(message = "A senha deve ter entre 5 e 30 caracteres", min = 5, max = 30)
    @Schema(description = "Senha do Usuário", example = "123456")
    String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "Data do Usuário", example = "1993-05-10")
    private LocalDate dataNasc;

    @NotNull(message = "O endereço não pode ser nulo")
    @Valid
    private EnderecoRequest endereco;

}
