package br.com.fiap.global.gateways.dtos.request;

import br.com.fiap.global.domains.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class UsuarioRequest extends RepresentationModel<UsuarioRequest> {

    @NotNull(message = "O nome de usuário não pode ser nulo")
    @Size(message = "O nome de usuário deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    String nome;

    @NotNull(message = "O sobrenome não pode ficar nulo")
    @Size(message = "O sobrenome deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    String sobrenome;

    @Size(message = "O telefone deve ter até 15 caracteres", max = 15)
    String telefone;

    @NotNull(message = "O e-mail não pode ser nulo")
    @Email(message = "O e-mail está inválido")
    String email;

    @NotNull(message = "A senha não pode ficar nula")
    @Size(message = "A senha deve ter até 100 caracteres", min = 5, max = 100)
    String senha;

    @NotNull(message = "O endereço não pode ser nulo")
    @Valid
    private EnderecoRequest endereco;

}
