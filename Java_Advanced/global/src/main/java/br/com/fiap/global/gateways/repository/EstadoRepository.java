package br.com.fiap.global.gateways.repository;

import br.com.fiap.global.domains.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}