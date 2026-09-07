package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.*;
import com.nexustitan.nexustitanapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreinoDiaService {
    private final TreinoDiaRepository r;
    private final TreinoRepository tr;

    public TreinoDiaService(TreinoDiaRepository r, TreinoRepository tr) {
        this.r = r;
        this.tr = tr;
    }

    public TreinoDia salvar(TreinoDia x) {
        if (x.getTreino() == null || x.getTreino().getId() == null)
            throw new IllegalArgumentException("Treino obrigatório");
        x.setTreino(tr.findById(x.getTreino().getId()).orElseThrow());
        boolean existe = r.findByTreinoId(x.getTreino().getId()).stream().anyMatch(d -> d.getDiaSemana().equalsIgnoreCase(x.getDiaSemana()));
        if (existe) throw new IllegalArgumentException("Este dia já existe neste treino");
        return r.save(x);
    }

    public List<TreinoDia> listarTodos() {
        return r.findAll();
    }

    public List<TreinoDia> porTreino(Long id) {
        return r.findByTreinoId(id);
    }

    public TreinoDia buscarPorId(Long id) {
        return r.findById(id).orElse(null);
    }

    public TreinoDia atualizar(Long id, TreinoDia x) {
        TreinoDia a = buscarPorId(id);
        if (a == null) return null;
        a.setDiaSemana(x.getDiaSemana());
        return r.save(a);
    }

    public void deletar(Long id) {
        r.deleteById(id);
    }
}