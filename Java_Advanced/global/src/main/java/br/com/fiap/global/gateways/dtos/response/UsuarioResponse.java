package br.com.fiap.global.gateways.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {

    Integer id;
    String nome;
    String sobrenome;
    String telefone;
    String email;
    LocalDate dataNasc;
    EnderecoResponse endereco;
}
