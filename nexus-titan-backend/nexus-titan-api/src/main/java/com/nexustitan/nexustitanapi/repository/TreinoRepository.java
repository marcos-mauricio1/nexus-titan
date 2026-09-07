package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
    List<Treino> findByAlunoId(Long alunoId);
}