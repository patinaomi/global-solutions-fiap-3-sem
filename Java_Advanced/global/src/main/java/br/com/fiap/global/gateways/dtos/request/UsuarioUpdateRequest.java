package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class UsuarioUpdateRequest extends RepresentationModel<UsuarioUpdateRequest> {

    @Size(message = "O nome de usuário deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    String nome;

    @Size(message = "O sobrenome deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    String sobrenome;

    @Size(message = "O telefone deve ter até 15 caracteres", max = 15)
    String telefone;

    @Email(message = "O e-mail está inválido")
    String email;

    @Size(message = "A senha deve ter até 100 caracteres", min = 5, max = 100)
    String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNasc;

    @Valid
    private EnderecoRequest endereco;
}
