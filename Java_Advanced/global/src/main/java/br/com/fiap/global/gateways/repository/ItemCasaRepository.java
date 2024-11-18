package br.com.fiap.global.gateways.repository;

import br.com.fiap.global.domains.ItemCasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCasaRepository extends JpaRepository<ItemCasa, Integer> {
}