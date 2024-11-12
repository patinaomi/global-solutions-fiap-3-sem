package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_formulario")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formulario", nullable = false)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50)
    private String sobrenome;

    @Column(length = 15)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 500)
    private String mensagem;
}
