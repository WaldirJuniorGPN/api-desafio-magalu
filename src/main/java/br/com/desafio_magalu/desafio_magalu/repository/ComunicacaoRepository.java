package br.com.desafio_magalu.desafio_magalu.repository;

import br.com.desafio_magalu.desafio_magalu.model.Comunicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long> {
}
