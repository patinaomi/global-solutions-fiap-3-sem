package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Login;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.LoginRepository;
import br.com.fiap.global.service.AuthenticationService;
import br.com.fiap.global.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationService service;
    private final LoginRepository repository;

    public Login register(String email, String senha) {
        Usuario usuario = service.authenticate(email, senha);

        Login login = new Login();
        login.setDataHora(LocalDateTime.now());
        login.setUsuario(usuario);

        return repository.save(login);
    }
}
