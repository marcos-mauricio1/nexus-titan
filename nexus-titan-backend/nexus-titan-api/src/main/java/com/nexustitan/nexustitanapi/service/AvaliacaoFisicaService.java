package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.dto.AvaliacaoFisicaRequestDTO;
import com.nexustitan.nexustitanapi.dto.AvaliacaoFisicaResponseDTO;
import com.nexustitan.nexustitanapi.exception.RecursoNaoEncontradoException;
import com.nexustitan.nexustitanapi.model.Aluno;
import com.nexustitan.nexustitanapi.model.AvaliacaoFisica;
import com.nexustitan.nexustitanapi.repository.AlunoRepository;
import com.nexustitan.nexustitanapi.repository.AvaliacaoFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service // @Service diz ao Spring: "essa classe contém regra de negócio, cria
// UMA instância dela (um "bean") e deixa disponível pra ser injetada
// em outras classes que precisarem dela" (no caso, o Controller).
public class AvaliacaoFisicaService {


    private final AvaliacaoFisicaRepository repository;


    private final AlunoRepository alunoRepository;

    public AvaliacaoFisicaService(AvaliacaoFisicaRepository repository, AlunoRepository alunoRepository) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
    }

    public AvaliacaoFisicaResponseDTO criar(AvaliacaoFisicaRequestDTO dto) {

        Aluno aluno = alunoRepository.findById(dto.getAlunoId()).orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado: id " + dto.getAlunoId()));

        AvaliacaoFisica avaliacao = AvaliacaoFisica.builder()
                .aluno(aluno)
                .peso(dto.getPeso())
                .altura(dto.getAltura())
                .dataAvaliacao(dto.getDataAvaliacao() != null ? dto.getDataAvaliacao() : LocalDate.now())
                .observacoes(dto.getObservacoes())
                .build();

        avaliacao.calcularImc();

        AvaliacaoFisica salva = repository.save(avaliacao); // vai salvar ou alterar(caso o id ja existir) no banco

        return paraResponseDTO(salva);

    }

    public AvaliacaoFisicaResponseDTO buscarPorId(Long id) {

        AvaliacaoFisica avaliacao = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Avaliação física não encontrada: id "+id));
        return paraResponseDTO(avaliacao);

    }

    public List<AvaliacaoFisicaResponseDTO> listarHistoricoDoAluno(Long alunoId) {

        return repository.findByAluno_IdOrderByDataAvaliacaoDesc(alunoId).stream()
                // .stream() transforma a lista numa "esteira" que permite aplicar operações em cada item, uma de cada vez.
                .map(this::paraResponseDTO)
                // .map() aplica o método paraResponseDTO em CADA avaliação da lista, convertendo Entity -> DTO uma por uma.
                .toList();
                // .toList() fecha a esteira e devolve uma List de novo.

    }

    public void excluir(Long id) {
        if(!repository.existsById(id)) {
            // existsById() só confere se existe, sem trazer o objeto inteiro do banco — mais rápido do que usar findById() só pra checar.
            throw new RecursoNaoEncontradoException("Avaliação física não encontrada: id "+id);
        }
        repository.deleteById(id);
    }

    // Método privado: só vai ser usado dentro da própria classe
    private AvaliacaoFisicaResponseDTO paraResponseDTO(AvaliacaoFisica avaliacao) {
        return AvaliacaoFisicaResponseDTO.builder()
                .id(avaliacao.getId())
                .alunoId(avaliacao.getAluno().getId())
                .peso(avaliacao.getPeso())
                .altura(avaliacao.getAltura())
                .imc(avaliacao.getImc())
                .classificacaoImc(classificar(avaliacao.getImc()))
                .dataAvaliacao(avaliacao.getDataAvaliacao())
                .observacoes(avaliacao.getObservacoes())
                .build();
    }

    // classificar imc
    private String classificar (Double imc) {
        if (imc == null) return "Não calculado";
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 25.0) return "Peso normal";
        if (imc < 30.0) return "Sobrepeso";
        if (imc < 35.0) return "Obesidade Grau I";
        if (imc < 40.0) return "Obesidade Grau II";
        return "Obesidade Grau III";
    }

}
