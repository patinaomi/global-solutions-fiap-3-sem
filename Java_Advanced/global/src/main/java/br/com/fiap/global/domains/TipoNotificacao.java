package br.com.fiap.global.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_tipo_notificacao")
public class TipoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_notif")
    private Integer id;

    @Column(name = "desc_tipo_notif", length = 50)
    private String descricao;

    @OneToMany(mappedBy = "tipoNotificacao")
    private List<Notificacao> notificacoes;

    @OneToMany(mappedBy = "tipoNotificacao")
        private List<ConfiguracaoUsuario> configuracoesUsuario;
}
