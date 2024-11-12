package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_tipo_dispositivo")
public class TipoDispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_dispositivo", nullable = false)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "t_tipo_dispositivo")
    private List<ItemCasa> itensCasa;
}
