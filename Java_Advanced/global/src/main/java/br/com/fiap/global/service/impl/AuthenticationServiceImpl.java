package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.UsuarioRepository;
import br.com.fiap.global.service.AuthenticationService;
import br.com.fiap.global.service.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsuarioRepository repository;

    public Usuario authenticate(String email, String senha) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new AuthenticationException("Usuário ou senha inválidos");
        }
        return usuario;
    }

    public Usuario findByEmailAndDateOfBirth(String email, LocalDate dataNasc) {
        return repository.findByEmailAndDataNasc(email, dataNasc);
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
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
}
