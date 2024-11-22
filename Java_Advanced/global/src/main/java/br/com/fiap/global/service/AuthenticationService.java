package br.com.fiap.global.service;

import br.com.fiap.global.domains.Usuario;

import java.time.LocalDate;


public interface AuthenticationService {
    Usuario authenticate(String email, String senha);

    Usuario findByEmailAndDateOfBirth(String email, LocalDate dataNasc);

    Usuario findByEmail(String email);
}
