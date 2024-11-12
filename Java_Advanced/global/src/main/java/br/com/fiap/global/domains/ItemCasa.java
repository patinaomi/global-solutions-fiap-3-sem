package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_item_casa")
public class ItemCasa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_casa", nullable = false)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_comodo")
    private Comodo comodo;

    @ManyToOne // afinal muiits itens de casa podem ter um tipo de dispositivo!
    @JoinColumn(name = "id_tipo_dispositivo")
    private TipoDispositivo tipoDispositivo;

    @OneToMany(mappedBy = "itemCasa")
    private List<Consumo> consumos;
}
