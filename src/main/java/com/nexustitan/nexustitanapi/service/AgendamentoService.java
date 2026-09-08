package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.dto.AgendamentoRequestDTO;
import com.nexustitan.nexustitanapi.dto.AgendamentoResponseDTO;
import com.nexustitan.nexustitanapi.exception.ConflitoHorarioException;
import com.nexustitan.nexustitanapi.exception.RecursoNaoEncontradoException;
import com.nexustitan.nexustitanapi.model.*;
import com.nexustitan.nexustitanapi.repository.AgendamentoRepository;
import com.nexustitan.nexustitanapi.repository.AlunoRepository;
import com.nexustitan.nexustitanapi.repository.DisponibilidadeRepository;
import com.nexustitan.nexustitanapi.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AgendamentoService {

    // Repare: esse Service precisa de QUATRO repositories diferentes,
    // porque ele mexe com Agendamento, mas também precisa CONSULTAR
    // Aluno, Personal e Disponibilidade pra fazer as validações.
    private final AgendamentoRepository agendamentoRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final AlunoRepository alunoRepository;
    private final PersonalRepository personalRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              DisponibilidadeRepository disponibilidadeRepository,
                              AlunoRepository alunoRepository,
                              PersonalRepository personalRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.alunoRepository = alunoRepository;
        this.personalRepository = personalRepository;
    }

    public AgendamentoResponseDTO criar (AgendamentoRequestDTO dto) {
        LocalDateTime inicio = dto.getDataHoraInicio();
        LocalDateTime fim = dto.getDataHoraFim();

        // Validação 1: início antes do fim
        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("dataHoraInicio deve ser antes de dataHoraFim");
        }
        // Validação 2: não deixa agendar no passado
        if (inicio.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar em um horário no passado");
        }

        // Buscar os dois relacionamentos no banco
        Aluno aluno = alunoRepository.findById(dto.getAlunoId()).orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado: id "+dto.getAlunoId()));

        Personal personal = personalRepository.findById(dto.getPersonalId()).orElseThrow(() -> new RecursoNaoEncontradoException("Personal não encontrado: id "+dto.getPersonalId()));

        // Validação 3: o personal tem disponibilidade cadastrada nesse horario?
        verificarDisponibilidade(dto.getPersonalId(), inicio, fim);

        // Validação 4: Já existe algum outro agendamento nesse horario?
        List<Agendamento> conflitos = agendamentoRepository.buscarConflitos(dto.getPersonalId(), StatusAgendamento.AGENDADO, inicio, fim);
        if (!conflitos.isEmpty()) {
            throw new ConflitoHorarioException("Já existe um agendamento para este personal nesse intervalo de horário");
        }

        // Passou por todas as validações para aí sim criar de fato
        Agendamento agendamento = Agendamento.builder()
                .aluno(aluno)
                .personal(personal)
                .dataHoraInicio(inicio)
                .dataHoraFim(fim)
                .status(StatusAgendamento.AGENDADO)
                .build();
        return paraResponseDTO(agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponseDTO cancelar (Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado: id " + id));

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new IllegalArgumentException("Este agendamento já está cancelado");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return paraResponseDTO(agendamentoRepository.save(agendamento));
    }

    public List<AgendamentoResponseDTO> listarPorAluno (Long alunoId) {
        return agendamentoRepository.findByAluno_Id(alunoId).stream().map(this::paraResponseDTO).toList();
    }

    public List<AgendamentoResponseDTO> listarPorPersonal (Long personalId) {
        return agendamentoRepository.findByPersonal_Id(personalId).stream().map(this::paraResponseDTO).toList();
    }

    // Confere se o horário pedido está DENTRO de algum bloco de
    // disponibilidade que o personal cadastrou naquele dia da semana.
    private void verificarDisponibilidade(Long personalId, LocalDateTime inicio, LocalDateTime fim) {

        if (!inicio.toLocalDate().equals(fim.toLocalDate())) {
            throw new IllegalArgumentException("O agendamento deve começar e terminar no mesmo dia");
        }

        DiaSemana diaSemana = converterDiaSemana(inicio.getDayOfWeek());
        LocalTime horaIniciio = inicio.toLocalTime();
        LocalTime horaFim = fim.toLocalTime();

        List<Disponibilidade> disponibilidades = disponibilidadeRepository.findByPersonal_IdAndDiaSemana(personalId, diaSemana);

        boolean coberto = disponibilidades.stream().anyMatch(d -> d.contemIntervalo(horaIniciio, horaFim));
        // .anyMatch() é parecido com o .map() que já vimos, mas em vez de
        // TRANSFORMAR cada item, ele só CHECA: "existe pelo menos UM item
        // na lista que satisfaça essa condição?" e devolve true/false.
        // Aqui: "existe algum bloco de disponibilidade que cubra esse horário?"

        if (!coberto) {
            throw new ConflitoHorarioException("O personal não possui disponibilidade cadastrada para esse dia/horário");
        }

    }

    // converte o "DayOFWeek" para o enum DiaSemana
    private DiaSemana converterDiaSemana (DayOfWeek dayOfWeek) {

        return switch (dayOfWeek) {
            case MONDAY -> DiaSemana.SEGUNDA;
            case TUESDAY -> DiaSemana.TERCA;
            case WEDNESDAY -> DiaSemana.QUARTA;
            case THURSDAY -> DiaSemana.QUINTA;
            case FRIDAY -> DiaSemana.SEXTA;
            case SATURDAY -> DiaSemana.SABADO;
            case SUNDAY -> DiaSemana.DOMINGO;
        };

    }

    private AgendamentoResponseDTO paraResponseDTO (Agendamento a) {

        return AgendamentoResponseDTO.builder()
                .id(a.getId())
                .alunoId(a.getAluno().getId())
                .personalId(a.getPersonal().getId())
                .dataHoraInicio(a.getDataHoraInicio())
                .dataHoraFim(a.getDataHoraFim())
                .status(a.getStatus()).build();

    }

}
