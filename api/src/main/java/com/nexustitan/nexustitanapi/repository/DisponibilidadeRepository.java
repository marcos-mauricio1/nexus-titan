package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.DiaSemana;
import com.nexustitan.nexustitanapi.model.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    List<Disponibilidade> findByPersonal_Id(Long personalId);
    // findBy + PersonalId = "busque todas as disponibilidades onde
    // personalId é igual ao valor que eu passar".
    // Usado pra: "mostrar TODOS os horários que um personal cadastrou".

    List<Disponibilidade> findByPersonal_IdAndDiaSemana(Long personalId, DiaSemana diaSeman);
    // O "And" no meio do nome do método junta DUAS condições:
    // WHERE personal_id = ? AND dia_semana = ?
    // Usado pra: "mostrar só os horários desse personal numa SEGUNDA,
    // por exemplo" — é essa consulta que o AgendamentoService vai usar
    // pra verificar se o personal está disponível naquele dia.

}
