package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    List<Exercicio> findByTreinoDiaId(Long treinoDiaId);
}