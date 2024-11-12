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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orcamento", nullable = false)
    private Integer id;

    @Column(name = "data_hora_visita", nullable = false)
    private LocalDate dataHoraVisita;

    @Column(name = "valor_orcamento", nullable = false)
    private Double valorOrcamento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
