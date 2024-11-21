package br.com.fiap.global.gateways.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest extends RepresentationModel<EnderecoRequest> {

    @NotNull(message = "O campo logradouro é obrigatório")
    @Size(message = "O campo logradouro deve ter entre 3 e 100 caracteres", min = 3, max = 100)
    @Schema(description = "Nome da Rua", example = "Avenida Paulista")
    private String logradouro;

    @NotNull(message = "O campo número é obrigatório")
    @Size(message = "O campo número deve ter no máximo 10 caracteres", max = 10)
    @Schema(description = "Número da Rua", example = "695")
    private String numero;

    @Size(message = "O campo complemento deve ter no máximo 50 caracteres", max = 50)
    @Schema(description = "Complemento", example = "Bloco A")
    private String complemento;

    @Size(message = "O campo bairro deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Bairro", example = "Brigadeiro")
    private String bairro;

    @NotNull(message = "O campo cidade é obrigatório")
    @Size(message = "O campo cidade deve ter entre 3 e 50 caracteres", min = 3, max = 50)
    @Schema(description = "Cidade", example = "São Paulo")
    private String cidade;

    @NotNull(message = "O campo cep é obrigatório")
    @Size(message = "O campo cep deve ter no máximo 10 caracteres", max = 10)
    @Schema(description = "Cep", example = "03043-010")
    private String cep;

    @NotNull(message = "O campo estadoId é obrigatório")
    @Schema(description = "Id do Estado", example = "1")
    private Integer estadoId;
}