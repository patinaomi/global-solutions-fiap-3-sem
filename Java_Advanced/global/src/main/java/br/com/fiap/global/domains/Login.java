package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login", nullable = false)
    private Integer id;

    @Column(name = "data_hora", nullable = false)
    private LocalDate dataHora;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
