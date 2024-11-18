package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.UsuarioRepository;
import br.com.fiap.global.service.AuthenticationService;
import br.com.fiap.global.service.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticaionServiceImpl implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;

    public Usuario authenticate(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new AuthenticationException("Usuário ou senha inválidos");
        }
        return usuario;
    }
}
