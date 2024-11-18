package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Consumo;
import br.com.fiap.global.service.ConsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumoServiceImpl implements ConsumoService {
    @Override
    public Consumo create(Consumo consumo) {
        return null;
    }

    @Override
    public Consumo findById(Integer id) {
        return null;
    }

    @Override
    public List<Consumo> findAll() {
        return List.of();
    }

    @Override
    public Consumo update(Integer id, Consumo consumo) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
