package br.com.fiap.global.gateways.repository;

import br.com.fiap.global.domains.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}