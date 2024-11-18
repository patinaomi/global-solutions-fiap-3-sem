package br.com.fiap.global.gateways.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
public class EnderecoRequest extends RepresentationModel<EnderecoRequest> {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private Integer estadoId;
}