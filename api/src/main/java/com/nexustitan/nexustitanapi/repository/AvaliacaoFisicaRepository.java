package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {

    List<AvaliacaoFisica> findByAluno_IdOrderByDataAvaliacaoDesc (Long alunoId);
    // O Spring lê o NOME do método e monta a consulta SQL sozinho,
    // interpretando palavra por palavra:
    //
    // findBy              -> "busque registros onde..."
    // AlunoId             -> "...o campo alunoId..."
    // OrderByDataAvaliacao -> "...ordenado pelo campo dataAvaliacao..."
    // Desc                -> "...do mais recente pro mais antigo"

}
