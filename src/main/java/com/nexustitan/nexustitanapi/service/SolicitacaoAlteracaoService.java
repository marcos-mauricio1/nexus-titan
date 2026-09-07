package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.SolicitacaoAlteracao;
import com.nexustitan.nexustitanapi.repository.SolicitacaoAlteracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoAlteracaoService {

    private final SolicitacaoAlteracaoRepository solicitacaoAlteracaoRepository;

    public SolicitacaoAlteracaoService(
            SolicitacaoAlteracaoRepository solicitacaoAlteracaoRepository) {

        this.solicitacaoAlteracaoRepository = solicitacaoAlteracaoRepository;
    }

    // Criar solicitação
    public SolicitacaoAlteracao salvar(SolicitacaoAlteracao solicitacao) {
        return solicitacaoAlteracaoRepository.save(solicitacao);
    }

    // Listar todas as solicitações
    public List<SolicitacaoAlteracao> listarTodos() {
        return solicitacaoAlteracaoRepository.findAll();
    }

    // Buscar solicitação por ID
    public SolicitacaoAlteracao buscarPorId(Long id) {
        return solicitacaoAlteracaoRepository.findById(id).orElse(null);
    }

    // Atualizar solicitação
    public SolicitacaoAlteracao atualizar(
            Long id,
            SolicitacaoAlteracao solicitacaoAtualizada) {

        SolicitacaoAlteracao solicitacao = buscarPorId(id);

        if (solicitacao == null) {
            return null;
        }

        solicitacao.setDescricao(solicitacaoAtualizada.getDescricao());
        solicitacao.setStatus(solicitacaoAtualizada.getStatus());
        solicitacao.setDataSolicitacao(
                solicitacaoAtualizada.getDataSolicitacao()
        );

        return solicitacaoAlteracaoRepository.save(solicitacao);
    }

    // Deletar solicitação
    public void deletar(Long id) {
        solicitacaoAlteracaoRepository.deleteById(id);
    }

    public SolicitacaoAlteracao atualizarStatus(Long id, String status) {

        SolicitacaoAlteracao solicitacao = buscarPorId(id);

        if (solicitacao == null) {
            return null;
        }

        solicitacao.setStatus(status);

        return solicitacaoAlteracaoRepository.save(solicitacao);
    }
}