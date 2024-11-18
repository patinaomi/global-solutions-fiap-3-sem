package br.com.fiap.global.gateways.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
public class ComodoResponse extends RepresentationModel<ComodoResponse> {

    private Integer id;
    private String descricao;

}
