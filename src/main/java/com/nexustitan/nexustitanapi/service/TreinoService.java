package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.Treino;
import com.nexustitan.nexustitanapi.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    // Criar treino
    public Treino salvar(Treino treino) {
        return treinoRepository.save(treino);
    }

    // Listar todos os treinos
    public List<Treino> listarTodos() {
        return treinoRepository.findAll();
    }

    // Buscar treino por ID
    public Treino buscarPorId(Long id) {
        return treinoRepository.findById(id).orElse(null);
    }

    // Atualizar treino
    public Treino atualizar(Long id, Treino treinoAtualizado) {

        Treino treino = buscarPorId(id);

        if (treino == null) {
            return null;
        }

        treino.setNome(treinoAtualizado.getNome());
        treino.setObjetivo(treinoAtualizado.getObjetivo());
        treino.setDescricao(treinoAtualizado.getDescricao());

        return treinoRepository.save(treino);
    }

    // Deletar treino
    public void deletar(Long id) {
        treinoRepository.deleteById(id);
    }
}