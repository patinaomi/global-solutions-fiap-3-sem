package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.ItemCasa;
import br.com.fiap.global.service.ItemCasaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCasaServiceImpl implements ItemCasaService {
    @Override
    public ItemCasa create(ItemCasa itemCasa) {
        return null;
    }

    @Override
    public ItemCasa findById(Integer id) {
        return null;
    }

    @Override
    public List<ItemCasa> findAll() {
        return List.of();
    }

    @Override
    public ItemCasa update(Integer id, ItemCasa itemCasa) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
