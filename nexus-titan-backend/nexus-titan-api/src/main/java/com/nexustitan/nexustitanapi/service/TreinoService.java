package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.*;
import com.nexustitan.nexustitanapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;

@Service
public class TreinoService {
    private final TreinoRepository r;
    private final AlunoRepository ar;
    private final PersonalRepository pr;

    public TreinoService(TreinoRepository r, AlunoRepository ar, PersonalRepository pr) {
        this.r = r;
        this.ar = ar;
        this.pr = pr;
    }

    public Treino salvar(Treino x) {
        if (x.getAluno() == null || x.getAluno().getId() == null || x.getPersonal() == null || x.getPersonal().getId() == null)
            throw new IllegalArgumentException("Aluno e personal são obrigatórios");
        x.setAluno(ar.findById(x.getAluno().getId()).orElseThrow());
        x.setPersonal(pr.findById(x.getPersonal().getId()).orElseThrow());
        if (x.getDataCriacao() == null) x.setDataCriacao(LocalDate.now());
        if (x.getAtivo() == null) x.setAtivo(true);
        return r.save(x);
    }

    public List<Treino> listarTodos() {
        return r.findAll();
    }

    public List<Treino> listarPorAluno(Long id) {
        return r.findByAlunoId(id);
    }

    public Treino buscarPorId(Long id) {
        return r.findById(id).orElse(null);
    }

    public Treino atualizar(Long id, Treino x) {
        Treino a = buscarPorId(id);
        if (a == null) return null;
        a.setNome(x.getNome());
        a.setTipoTreino(x.getTipoTreino());
        a.setObjetivo(x.getObjetivo());
        a.setDescricao(x.getDescricao());
        a.setAtivo(x.getAtivo() == null ? a.getAtivo() : x.getAtivo());
        if (x.getAluno() != null && x.getAluno().getId() != null)
            a.setAluno(ar.findById(x.getAluno().getId()).orElseThrow());
        if (x.getPersonal() != null && x.getPersonal().getId() != null)
            a.setPersonal(pr.findById(x.getPersonal().getId()).orElseThrow());
        return r.save(a);
    }

    public void deletar(Long id) {
        r.deleteById(id);
    }
}