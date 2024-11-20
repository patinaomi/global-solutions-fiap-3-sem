package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ItemCasaRequest extends RepresentationModel<ItemCasaRequest> {

    @NotNull(message = "O campo descrição é obrigatório")
    @Size(min = 3, max = 50, message = "O campo descrição deve ter entre 3 e 50 caracteres")
    @Schema(description = "Descrição do item da casa", example = "Microondas")
    private String descricao;

}
