package br.com.fiap.global.gateways.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


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