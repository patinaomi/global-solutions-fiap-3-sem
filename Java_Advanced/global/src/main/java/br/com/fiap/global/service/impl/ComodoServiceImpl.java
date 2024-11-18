package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Comodo;
import br.com.fiap.global.gateways.repository.ComodoRepository;
import br.com.fiap.global.service.ComodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComodoServiceImpl implements ComodoService {

    private final ComodoRepository repository;

    @Override
    public Comodo create(Comodo comodo) {
        return null;
    }

    @Override
    public Comodo findById(Integer id) {
        return null;
    }

    @Override
    public List<Comodo> findAll() {
        return List.of();
    }

    @Override
    public Comodo update(Integer id, Comodo comodo) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
