package br.com.fiap.global.gateways.dtos.request;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class EnderecoRequest extends RepresentationModel<EnderecoRequest> {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private Integer estadoId;
}