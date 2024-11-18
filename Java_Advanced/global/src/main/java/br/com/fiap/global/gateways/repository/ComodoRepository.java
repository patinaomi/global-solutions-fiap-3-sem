package br.com.fiap.global.gateways.repository;

import br.com.fiap.global.domains.Comodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComodoRepository extends JpaRepository<Comodo, Integer> {
}