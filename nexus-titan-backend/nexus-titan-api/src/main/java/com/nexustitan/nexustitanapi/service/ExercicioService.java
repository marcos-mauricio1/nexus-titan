package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.*;
import com.nexustitan.nexustitanapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExercicioService {
    private final ExercicioRepository r;
    private final TreinoDiaRepository dr;

    public ExercicioService(ExercicioRepository r, TreinoDiaRepository dr) {
        this.r = r;
        this.dr = dr;
    }

    public Exercicio salvar(Exercicio x) {
        if (x.getTreinoDia() == null || x.getTreinoDia().getId() == null)
            throw new IllegalArgumentException("Dia obrigatório");
        x.setTreinoDia(dr.findById(x.getTreinoDia().getId()).orElseThrow());
        return r.save(x);
    }

    public List<Exercicio> listarTodos() {
        return r.findAll();
    }

    public List<Exercicio> porDia(Long id) {
        return r.findByTreinoDiaId(id);
    }

    public Exercicio buscarPorId(Long id) {
        return r.findById(id).orElse(null);
    }

    public Exercicio atualizar(Long id, Exercicio x) {
        Exercicio a = buscarPorId(id);
        if (a == null) return null;
        a.setNome(x.getNome());
        a.setDescricao(x.getDescricao());
        a.setSeries(x.getSeries());
        a.setRepeticoes(x.getRepeticoes());
        a.setTempoDescanso(x.getTempoDescanso());
        a.setCarga(x.getCarga());
        return r.save(a);
    }

    public void deletar(Long id) {
        r.deleteById(id);
    }
}