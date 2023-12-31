package br.com.amorim.minhasfinancas.service.impl;

import br.com.amorim.minhasfinancas.exception.RegraNegocioException;
import br.com.amorim.minhasfinancas.model.entity.Lancamento;
import br.com.amorim.minhasfinancas.model.enums.StatusLancamento;
import br.com.amorim.minhasfinancas.model.repository.LancamentoRepository;
import br.com.amorim.minhasfinancas.service.LancamentoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public abstract class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository repository;

    public LancamentoServiceImpl(LancamentoRepository repository) {
        this.repository = repository;
    }
    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        return repository.save(lancamento);

    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects .requireNonNull(lancamento.getId());
        validar(lancamento);
        return repository.save(lancamento);
    }

    @Override
    public void deletar(Lancamento lancamento) {
        Objects .requireNonNull(lancamento.getId());
        repository.delete(lancamento);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example<Lancamento> example = Example.of(lancamentoFiltro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example);
    }

    @Override
    @Transactional
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatus(status);
        atualizar(lancamento);

    }
    @Override
    public void validar(Lancamento lancamento) {
        if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().isEmpty()){
            throw new RegraNegocioException("Informe uma desricao valida");
        }

        if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
            throw new RegraNegocioException("Informe um Mês valido.");
        }
        if(lancamento.getAno() == null || lancamento.getAno().toString().length() !=4){
            throw new RegraNegocioException("Informe um Ano válido");
        }
        if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
            throw new RegraNegocioException("Informe um Usuario.");
        }

        if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO)< 1){
                throw new RegraNegocioException("Informe um valor valido.");
        }

        if(lancamento.getTipo() == null){
            throw new RegraNegocioException("Informe um tipo de lancamento");
        }
    }
}
