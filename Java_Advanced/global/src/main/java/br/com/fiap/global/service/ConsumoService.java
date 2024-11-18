package br.com.fiap.global.service;

import br.com.fiap.global.domains.Consumo;

import java.util.List;

public interface ConsumoService {
    Consumo create(Consumo consumo);

    Consumo findById(Integer id);

    List<Consumo> findAll();

    Consumo update(Integer id, Consumo consumo);

    void delete(Integer id);
}
