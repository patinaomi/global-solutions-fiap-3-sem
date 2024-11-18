package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
public class EnderecoRequest extends RepresentationModel<EnderecoRequest> {

    @NotNull(message = "O campo logradouro é obrigatório")
    @Size(message = "O campo logradouro deve ter entre 3 e 100 caracteres", min = 3, max = 100)
    private String logradouro;

    @NotNull(message = "O campo número é obrigatório")
    @Size(message = "O campo número deve ter no máximo 10 caracteres", max = 10)
    private String numero;

    @Size(message = "O campo complemento deve ter no máximo 50 caracteres", max = 50)
    private String complemento;

    @Size(message = "O campo bairro deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    private String bairro;

    @NotNull(message = "O campo cidade é obrigatório")
    @Size(message = "O campo cidade deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    private String cidade;

    @NotNull(message = "O campo cep é obrigatório")
    @Size(message = "O campo cep deve ter no máximo 10 caracteres", max = 10)
    private String cep;

    @NotNull(message = "O campo estadoId é obrigatório")
    private Integer estadoId;
}