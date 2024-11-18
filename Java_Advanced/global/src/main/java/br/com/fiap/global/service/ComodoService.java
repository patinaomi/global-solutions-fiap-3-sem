package br.com.fiap.global.service;

import br.com.fiap.global.domains.Comodo;

import java.util.List;

public interface ComodoService {
    Comodo create(Comodo comodo);

    Comodo findById(Integer id);

    List<Comodo> findAll();

    Comodo update(Integer id, Comodo comodo);

    void delete(Integer id);
}
