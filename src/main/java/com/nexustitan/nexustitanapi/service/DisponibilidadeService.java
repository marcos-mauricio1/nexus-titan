package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.dto.DisponibilidadeRequestDTO;
import com.nexustitan.nexustitanapi.dto.DisponibilidadeResponseDTO;
import com.nexustitan.nexustitanapi.exception.RecursoNaoEncontradoException;
import com.nexustitan.nexustitanapi.model.DiaSemana;
import com.nexustitan.nexustitanapi.model.Disponibilidade;
import com.nexustitan.nexustitanapi.model.Personal;
import com.nexustitan.nexustitanapi.repository.DisponibilidadeRepository;
import com.nexustitan.nexustitanapi.repository.PersonalRepository;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository repository;
    private final PersonalRepository personalRepository;

    public DisponibilidadeService (DisponibilidadeRepository repository, PersonalRepository personalRepository) {
        this.repository = repository;
        this.personalRepository = personalRepository;
    }

    public DisponibilidadeResponseDTO cadastrar (DisponibilidadeRequestDTO dto) {
        if (!dto.getHoraInicio().isBefore(dto.getHoraFim())) {
            throw new IllegalArgumentException("horaInicio deve ser antes de horaFim");
        }

        // Busca o personal de verdade no banco
        Personal personal = personalRepository.findById(dto.getPersonalId()).orElseThrow(() -> new RecursoNaoEncontradoException("Personal não encontrado: id "+dto.getPersonalId()));

        Disponibilidade disponibilidade = Disponibilidade.builder()
                .personal(personal)
                .diaSemana(dto.getDiaSemana())
                .horaInicio(dto.getHoraInicio())
                .horaFim(dto.getHoraFim()).build();

        return paraResponseDTO(repository.save(disponibilidade));
    }

    public DisponibilidadeResponseDTO editar(Long id, DisponibilidadeRequestDTO dto) {

        // Busca a disponibilidade que já existe
        Disponibilidade disponibilidade = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Disponibilidade não encontrada: id "+id));

        if (!dto.getHoraInicio().isBefore(dto.getHoraFim())) {
            throw new IllegalArgumentException("horaInicio deve ser antes de horaFim");
        }

        // Repare: aqui a gente SÓ troca os campos, não cria um objeto novo.
        // Isso é importante porque o JPA já está "rastreando" esse objeto
        // (ele sabe que veio do banco), então ao chamar save() de novo,
        // ele faz um UPDATE na mesma linha, não um INSERT novo.
        disponibilidade.setDiaSemana(dto.getDiaSemana());
        disponibilidade.setHoraInicio(dto.getHoraInicio());
        disponibilidade.setHoraFim(dto.getHoraFim());

        return paraResponseDTO(repository.save(disponibilidade));

    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Disponibilidade não encontrada: id "+id);
        }
        repository.deleteById(id);
    }

    public List<DisponibilidadeResponseDTO> consultarPorPersonalEDia(Long personalId, DiaSemana diaSemana) {
        return repository.findByPersonal_IdAndDiaSemana(personalId, diaSemana).stream()
                .map(this::paraResponseDTO)
                .toList();
    }

    public List<DisponibilidadeResponseDTO> listarPorPersonal (Long personalId) {
        return repository.findByPersonal_Id(personalId).stream().map(this::paraResponseDTO).toList();
    }

    private DisponibilidadeResponseDTO paraResponseDTO (Disponibilidade d) {
        return DisponibilidadeResponseDTO.builder()
                .id(d.getId())
                .personalId(d.getPersonal().getId())
                .diaSemana(d.getDiaSemana())
                .horaInicio(d.getHoraInicio())
                .horaFim(d.getHoraFim())
                .build();
    }

}
