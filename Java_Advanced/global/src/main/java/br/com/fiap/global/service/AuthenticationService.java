package br.com.fiap.global.service;

import br.com.fiap.global.domains.Usuario;


public interface AuthenticationService {
    Usuario authenticate(String email, String senha);
}
