package br.com.amorim.minhasfinancas.model.repository;

import br.com.amorim.minhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface lancamentoRepository extends JpaRepository<Lancamento, Long> {
}
