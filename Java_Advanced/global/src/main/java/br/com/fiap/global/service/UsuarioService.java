package br.com.fiap.global.service;

import br.com.fiap.global.domains.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {

    Usuario create(Usuario usuario);
    Usuario findById(Integer id);
    List<Usuario> findAll();
    Usuario update(Integer id, Usuario usuario);
    void delete(Integer id);

}
