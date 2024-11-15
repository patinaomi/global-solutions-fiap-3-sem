package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.repository.UsuarioRepository;
import br.com.fiap.global.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl extends UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario create(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario findById(String id) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario update(String id, Usuario usuario) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
