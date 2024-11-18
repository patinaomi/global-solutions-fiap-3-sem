package br.com.fiap.global.domains;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "t_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String sobrenome;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String senha;

    @Column(name = "data_nasc", nullable = false)
    private LocalDate dataNasc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Login> logins = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comodo> comodos;

    @OneToMany(mappedBy = "usuario")
    private List<Recomendacao> recomendacoes;

    @OneToMany(mappedBy = "usuario")
    private List<Consumo> consumos;

}
