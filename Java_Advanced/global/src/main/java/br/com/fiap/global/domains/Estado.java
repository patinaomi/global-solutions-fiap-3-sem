package br.com.fiap.global.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "t_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Integer id;

    @Column(length = 2, nullable = false)
    private String sigla;

    @OneToMany(mappedBy = "t_estado")
    private List<Endereco> enderecos = new ArrayList<>();

}
