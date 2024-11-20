package br.com.fiap.global.gateways.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
public class ItemCasaResponse extends RepresentationModel<ItemCasaResponse> {

    private Integer id;

    private String descricao;

}
