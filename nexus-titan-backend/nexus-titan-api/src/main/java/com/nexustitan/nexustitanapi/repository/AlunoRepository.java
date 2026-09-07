package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}