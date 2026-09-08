package com.nexustitan.nexustitanapi.repository;

import com.nexustitan.nexustitanapi.model.Agendamento;
import com.nexustitan.nexustitanapi.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {

    List<Agendamento> findByAluno_Id(Long alunoId);
    // "Liste todos os agendamentos desse aluno" — pra tela de
    // "meus agendamentos" do aluno.

    List<Agendamento> findByPersonal_Id(Long personalId);

    @Query("SELECT a FROM Agendamento a WHERE a.personal.id = :personalId " +
            "AND a.status = :status "+
            "AND a.dataHoraInicio < :fim AND a.dataHoraFim > :inicio")
    // repare que usamos "Agendamento"
    // (nome da CLASSE Java) e "a.personalId" (nome do CAMPO Java),
    // não "agendamento" e "personal_id" (nomes da tabela/coluna do banco).
    // O Hibernate traduz isso pra SQL de verdade na hora de rodar.

    List<Agendamento> buscarConflitos(@Param("personalId") Long personalId,
                                      @Param("status") StatusAgendamento status,
                                      @Param("inicio") LocalDateTime inicio,
                                      @Param("fim") LocalDateTime fim);
    // @Param liga cada ":nomeDoParametro" escrito lá em cima na @Query
    // com o parâmetro correspondente aqui do método Java.
    // Sem essa "ponte", o Spring não saberia qual valor colocar em
    // cada ":personalId", ":inicio", ":fim" dentro da consulta.

}
