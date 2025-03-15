package br.com.desafio_magalu.desafio_magalu.repository;

import br.com.desafio_magalu.desafio_magalu.model.Comunicacado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComunicacaoRepository extends JpaRepository<Comunicacado, Long> {

    Optional<Comunicacado> findById(Long id);
}
