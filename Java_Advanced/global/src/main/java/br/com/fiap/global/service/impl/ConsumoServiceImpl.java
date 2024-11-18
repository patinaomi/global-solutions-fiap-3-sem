package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Consumo;
import br.com.fiap.global.gateways.repository.ConsumoRepository;
import br.com.fiap.global.service.ConsumoService;
import br.com.fiap.global.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumoServiceImpl implements ConsumoService {

    private final ConsumoRepository repository;

    @Override
    public Consumo create(Consumo consumo) {
        return repository.save(consumo);
    }

    @Override
    public Consumo findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Consumo.class.getName()));
    }

    @Override
    public List<Consumo> findAll() {
        List<Consumo> consumos = repository.findAll();
        if (consumos.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum consumo encontrado.");
        }
        return consumos;
    }

    @Override
    public Consumo update(Integer id, Consumo consumo) {
        Consumo consumoExists = findById(id);

        consumo.setId(consumoExists.getId());
        return repository.save(consumo);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ObjectNotFoundException("Não pode ser Deletado! Id não encontrado: " + id + ", Tipo: " + Consumo.class.getName());
        }
    }
}
