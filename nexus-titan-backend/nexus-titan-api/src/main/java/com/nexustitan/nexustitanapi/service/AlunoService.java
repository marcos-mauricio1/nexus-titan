package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.Aluno;
import com.nexustitan.nexustitanapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlunoService {
    private final AlunoRepository r;

    public AlunoService(AlunoRepository r) {
        this.r = r;
    }

    public Aluno salvar(Aluno x) {
        return r.save(x);
    }

    public List<Aluno> listarTodos() {
        return r.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return r.findById(id).orElse(null);
    }

    public Aluno atualizar(Long id, Aluno x) {
        Aluno a = buscarPorId(id);
        if (a == null) return null;
        a.setNome(x.getNome());
        a.setEmail(x.getEmail());
        a.setIdade(x.getIdade());
        a.setPeso(x.getPeso());
        a.setAltura(x.getAltura());
        a.setGenero(x.getGenero());
        return r.save(a);
    }

    public void deletar(Long id) {
        r.deleteById(id);
    }
}