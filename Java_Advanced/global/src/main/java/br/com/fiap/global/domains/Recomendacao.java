package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table
@Entity(name = "t_recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recomendacao", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_comodo")
    private Comodo comodo;

    @ManyToOne
    @JoinColumn(name = "id_item_casa")
    private ItemCasa itemCasa;

    @ManyToOne
    @JoinColumn(name = "id_consumo")
    private Consumo consumo;

    @Column(name = "consumo")
    private Double consumoModelo; // aqui é a amperagem do modelo treinado

    @Column(name = "valor_previsto")
    private Double valorPrevisto;

    @Column(name = "variacao_consumo")
    private Double variacaoConsumo;

    @Column(name = "sugestao_melhoria")
    private String sugestaoMelhoria;

    @Column(name = "data_recomendacao")
    private LocalDate dataRecomendacao;

}
