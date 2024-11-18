package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.Comodo;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.ComodoRepository;
import br.com.fiap.global.service.ComodoService;
import br.com.fiap.global.service.exception.DataIntegrityException;
import br.com.fiap.global.service.exception.EntityNotFoundException;
import br.com.fiap.global.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComodoServiceImpl implements ComodoService {

    private final ComodoRepository repository;

    @Override
    public Comodo create(Comodo comodo) {
        return repository.save(comodo);
    }

    @Override
    public Comodo findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Comodo.class.getName()));
    }

    @Override
    public List<Comodo> findAll() {
        List<Comodo> comodos = repository.findAll();
        if (comodos.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum cômodo encontrado.");
        }
        return comodos;
    }

    @Override
    public Comodo update(Integer id, Comodo comodo) {
        Comodo comodoExists = findById(id);

        comodo.setId(comodoExists.getId());
        return repository.save(comodo);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não pode ser Deletado! Id não encontrado: " + id + ", Tipo: " + Usuario.class.getName());
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser Deletado! O registro está relacionado a outros dados.");
        }
    }
}
