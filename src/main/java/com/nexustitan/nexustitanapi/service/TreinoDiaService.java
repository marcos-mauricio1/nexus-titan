package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.TreinoDia;
import com.nexustitan.nexustitanapi.repository.TreinoDiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoDiaService {

    private final TreinoDiaRepository treinoDiaRepository;

    public TreinoDiaService(TreinoDiaRepository treinoDiaRepository) {
        this.treinoDiaRepository = treinoDiaRepository;
    }

    // Criar
    public TreinoDia salvar(TreinoDia treinoDia) {
        return treinoDiaRepository.save(treinoDia);
    }

    // Listar todos
    public List<TreinoDia> listarTodos() {
        return treinoDiaRepository.findAll();
    }

    // Buscar por ID
    public TreinoDia buscarPorId(Long id) {
        return treinoDiaRepository.findById(id).orElse(null);
    }

    // Atualizar
    public TreinoDia atualizar(Long id, TreinoDia treinoDiaAtualizado) {

        TreinoDia treinoDia = buscarPorId(id);

        if (treinoDia == null) {
            return null;
        }

        treinoDia.setDiaSemana(treinoDiaAtualizado.getDiaSemana());
        treinoDia.setTreino(treinoDiaAtualizado.getTreino());

        return treinoDiaRepository.save(treinoDia);
    }

    // Deletar
    public void deletar(Long id) {
        treinoDiaRepository.deleteById(id);
    }
}