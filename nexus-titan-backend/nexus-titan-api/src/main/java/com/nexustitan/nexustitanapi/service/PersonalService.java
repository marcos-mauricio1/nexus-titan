package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.Personal;
import com.nexustitan.nexustitanapi.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonalService {
    private final PersonalRepository r;

    public PersonalService(PersonalRepository r) {
        this.r = r;
    }

    public Personal salvar(Personal x) {
        return r.save(x);
    }

    public List<Personal> listarTodos() {
        return r.findAll();
    }

    public Personal buscarPorId(Long id) {
        return r.findById(id).orElse(null);
    }

    public Personal atualizar(Long id, Personal x) {
        Personal a = buscarPorId(id);
        if (a == null) return null;
        a.setNome(x.getNome());
        a.setIdade(x.getIdade());
        a.setCpf(x.getCpf());
        a.setGenero(x.getGenero());
        a.setTipoPersonal(x.getTipoPersonal());
        return r.save(a);
    }

    public void deletar(Long id) {
        r.deleteById(id);
    }
}