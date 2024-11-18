package br.com.fiap.global.service;

import br.com.fiap.global.domains.Login;
import br.com.fiap.global.domains.Usuario;

public interface LoginService {

    Login register(String email, String senha);
}
