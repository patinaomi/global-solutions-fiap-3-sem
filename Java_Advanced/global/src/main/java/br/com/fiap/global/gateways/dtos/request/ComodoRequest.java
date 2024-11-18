package br.com.fiap.global.gateways.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ComodoRequest extends RepresentationModel<ComodoRequest> {

    @NotNull(message = "O campo descrição é obrigatório")
    @Size(message = "O campo descrição deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    private String descricao;

}
