package br.com.amorim.minhasfinancas.service;

import br.com.amorim.minhasfinancas.model.entity.Lancamento;
import br.com.amorim.minhasfinancas.model.enums.StatusLancamento;

import java.util.List;


public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);
    Lancamento atualizar(Lancamento lancamento);
    void deletar(Lancamento lancamento);
    List<Lancamento> buscar (Lancamento lancamentoFiltro);
    void atualizarStatus(Lancamento lancamento, StatusLancamento status);
    void validar(Lancamento lancamento);
}
