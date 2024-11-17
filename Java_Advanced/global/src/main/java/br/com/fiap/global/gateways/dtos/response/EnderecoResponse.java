package br.com.fiap.global.gateways.dtos.response;

import br.com.fiap.global.domains.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Data
public class EnderecoResponse extends RepresentationModel<EnderecoResponse> {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private Integer estadoId;
}
