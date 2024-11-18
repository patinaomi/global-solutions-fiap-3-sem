package br.com.fiap.global.gateways.repository;

import br.com.fiap.global.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);

    Usuario findByEmailAndDataNasc(String email, LocalDate dataNasc);
}
