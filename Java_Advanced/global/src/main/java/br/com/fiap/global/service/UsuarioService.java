package br.com.fiap.global.service;

import br.com.fiap.global.domains.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario create(Usuario usuario);

    Usuario findById(Integer id);

    List<Usuario> findAll();

    Usuario update(Integer id, Usuario usuario);

    void delete(Integer id);

    boolean updatePassword(Integer usuarioId, String novaSenha);

}
