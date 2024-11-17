package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.UsuarioRepository;
import br.com.fiap.global.service.UsuarioService;
import br.com.fiap.global.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public Usuario create(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario update(Integer id, Usuario usuario) {
        if(repository.existsById(id)) {
            usuario.setId(id);
            return repository.save(usuario);
        } else {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
        }
    }

    @Override
    public void delete(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
        }
    }
}
