package br.com.fiap.global.service;

import br.com.fiap.global.domains.ItemCasa;

import java.util.List;

public interface ItemCasaService {

    ItemCasa create(ItemCasa itemCasa);

    ItemCasa findById(Integer id);

    List<ItemCasa> findAll();

    ItemCasa update(Integer id, ItemCasa itemCasa);

    void delete(Integer id);
}
