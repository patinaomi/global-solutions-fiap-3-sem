package br.com.fiap.global.service;

import br.com.fiap.global.domains.Login;

public interface LoginService {

    Login register(String email, String senha);
}
