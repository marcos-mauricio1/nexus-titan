package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.Exercicio;
import com.nexustitan.nexustitanapi.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    // Criar exercício
    public Exercicio salvar(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    // Listar todos os exercícios
    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    // Buscar exercício por ID
    public Exercicio buscarPorId(Long id) {
        return exercicioRepository.findById(id).orElse(null);
    }

    // Atualizar exercício
    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {

        Exercicio exercicio = buscarPorId(id);

        if (exercicio == null) {
            return null;
        }

        exercicio.setNome(exercicioAtualizado.getNome());
        exercicio.setDescricao(exercicioAtualizado.getDescricao());
        exercicio.setSeries(exercicioAtualizado.getSeries());
        exercicio.setRepeticoes(exercicioAtualizado.getRepeticoes());
        exercicio.setCarga(exercicioAtualizado.getCarga());
        exercicio.setTempoDescanso(exercicioAtualizado.getTempoDescanso());
        exercicio.setTreinoDia(exercicioAtualizado.getTreinoDia());

        return exercicioRepository.save(exercicio);
    }

    // Deletar exercício
    public void deletar(Long id) {
        exercicioRepository.deleteById(id);
    }
}