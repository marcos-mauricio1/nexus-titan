package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.*;
import com.nexustitan.nexustitanapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;

@Service
public class SolicitacaoAlteracaoService {
    private final SolicitacaoAlteracaoRepository r;

    public SolicitacaoAlteracaoService(SolicitacaoAlteracaoRepository r) {
        this.r = r;
    }

    public SolicitacaoAlteracao salvar(SolicitacaoAlteracao x) {
        if (x.getStatus() == null || x.getStatus().isBlank()) x.setStatus("PENDENTE");
        if (x.getDataSolicitacao() == null) x.setDataSolicitacao(LocalDate.now());
        return r.save(x);
    }

    public List<SolicitacaoAlteracao> listarTodos() {
        return r.findAll();
    }

    public SolicitacaoAlteracao buscarPorId(Long id) {
        return r.findById(id).orElse(null);
    }

    public SolicitacaoAlteracao atualizar(Long id, SolicitacaoAlteracao x) {
        SolicitacaoAlteracao a = buscarPorId(id);
        if (a == null) return null;
        a.setMotivo(x.getMotivo());
        a.setDescricao(x.getDescricao());
        a.setStatus(x.getStatus());
        return r.save(a);
    }

    public SolicitacaoAlteracao atualizarStatus(Long id, String status) {
        SolicitacaoAlteracao a = buscarPorId(id);
        if (a == null) return null;
        a.setStatus(status);
        return r.save(a);
    }

    public void deletar(Long id) {
        r.deleteById(id);
    }
}