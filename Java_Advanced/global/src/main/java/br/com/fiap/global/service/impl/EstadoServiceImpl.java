package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Estado;
import br.com.fiap.global.gateways.repository.EstadoRepository;
import br.com.fiap.global.service.EstadoService;
import br.com.fiap.global.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository repository;

    @Override
    public Estado findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }
}
