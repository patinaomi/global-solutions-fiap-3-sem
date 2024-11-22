package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.UsuarioRepository;
import br.com.fiap.global.service.UsuarioService;
import br.com.fiap.global.service.exception.DataIntegrityException;
import br.com.fiap.global.service.exception.EntityNotFoundException;
import br.com.fiap.global.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final EmailServiceImpl service;

    @Override
    public Usuario create(Usuario usuario) {
        usuario.setTelefone(limparCaracteresTel(usuario.getTelefone()));
        Usuario usuarioSalvo = repository.save(usuario);

        String msgEmail = String.format(
                "Olá, %s! Seu cadastro foi realizado com sucesso!", usuario.getNome()
        );

        // Enviar e-mail de confirmação
        service.sendEmail(usuario.getEmail(), "Cadastro realizado com sucesso", msgEmail);
        return usuarioSalvo;
    }

    @Override
    public Usuario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("Nenhum usuário encontrado.");
        }
        return usuarios;
    }

    @Override
    public Usuario update(Integer id, Usuario usuario) {
        Usuario usuarioExists = findById(id);

        usuario.setId(usuarioExists.getId());
        usuario.setTelefone(limparCaracteresTel(usuario.getTelefone()));

        return repository.save(usuario);
    }


    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não pode ser Deletado! Id não encontrado: " + id + ", Tipo: " + Usuario.class.getName());
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser Deletado! O registro está relacionado a outros dados.");
        }
    }

    @Override
    public boolean updatePassword(Integer usuarioId, String novaSenha) {
        Optional<Usuario> usuarioOptional = repository.findById(usuarioId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setSenha(novaSenha);
            repository.save(usuario);
            return true;
        } else {
            return false;
        }
    }


    // Método utilitário para limpar caracteres não numéricos do telefone
    private String limparCaracteresTel(String telefone) {
        return telefone != null ? telefone.replaceAll("\\D", "") : null;
    }
}
