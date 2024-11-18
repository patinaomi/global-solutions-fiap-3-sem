package br.com.fiap.global.service.impl;

import br.com.fiap.global.domains.ItemCasa;
import br.com.fiap.global.domains.Usuario;
import br.com.fiap.global.gateways.repository.ItemCasaRepository;
import br.com.fiap.global.service.ItemCasaService;
import br.com.fiap.global.service.exception.DataIntegrityException;
import br.com.fiap.global.service.exception.EntityNotFoundException;
import br.com.fiap.global.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCasaServiceImpl implements ItemCasaService {

    private final ItemCasaRepository repository;

    @Override
    public ItemCasa create(ItemCasa itemCasa) {
        return repository.save(itemCasa);
    }

    @Override
    public ItemCasa findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ItemCasa.class.getName()));
    }

    @Override
    public List<ItemCasa> findAll() {
        List<ItemCasa> itensCasa = repository.findAll();
        if (itensCasa.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum item de casa encontrado.");
        }
        return itensCasa;
    }

    @Override
    public ItemCasa update(Integer id, ItemCasa itemCasa) {
        ItemCasa itensExists = findById(id);

        itemCasa.setId(itensExists.getId());
        return repository.save(itemCasa);
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
