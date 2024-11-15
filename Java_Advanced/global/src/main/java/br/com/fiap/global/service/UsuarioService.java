package br.com.fiap.global.service;

import br.com.fiap.global.domains.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario create(Usuario usuario);
    Usuario findById(String id);
    List<Usuario> findAll();
    Usuario update(String id, Usuario usuario);
    void delete(String id);

}
