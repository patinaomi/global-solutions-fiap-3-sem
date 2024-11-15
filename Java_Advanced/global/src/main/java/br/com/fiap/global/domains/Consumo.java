package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table
@Entity(name = "t_consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo", nullable = false)
    private Integer id;

    private Double consumo;

    @Column(name = "data_consumo")
    private LocalDate dataConsumo;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_item_casa")
    private ItemCasa itemCasa;

    @OneToMany(mappedBy = "consumo")
    private List<Recomendacao> recomendacoes;
}
