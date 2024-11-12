package br.com.fiap.global.domains;

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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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

        @Column(length = 100, nullable = false)
        private String senha;

        @OneToOne
        @JoinColumn(name = "id_endereco")
        private Endereco endereco;

        @OneToOne(mappedBy = "usuario")
        private Login login;

        @OneToMany(mappedBy = "usuario")
        private List<Comodo> comodos;

        @OneToMany(mappedBy = "usuario")
        private List<Orcamento> orcamentos;
}
