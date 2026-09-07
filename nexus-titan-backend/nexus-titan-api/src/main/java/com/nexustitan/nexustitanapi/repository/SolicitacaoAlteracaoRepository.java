package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.SolicitacaoAlteracao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoAlteracaoRepository extends JpaRepository<SolicitacaoAlteracao, Long> {
}