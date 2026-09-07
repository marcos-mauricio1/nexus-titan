package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.TreinoDia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreinoDiaRepository extends JpaRepository<TreinoDia, Long> {
    List<TreinoDia> findByTreinoId(Long treinoId);
}