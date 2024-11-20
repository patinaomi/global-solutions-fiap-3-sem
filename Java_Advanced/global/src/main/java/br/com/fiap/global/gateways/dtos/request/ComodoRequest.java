package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
public class ComodoRequest extends RepresentationModel<ComodoRequest> {

    @NotNull(message = "O campo descrição é obrigatório")
    @Size(message = "O campo descrição deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Nome do cômodo", example = "Quarto")
    private String descricao;

}
