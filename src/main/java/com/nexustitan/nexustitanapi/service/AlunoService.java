package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.Aluno;
import com.nexustitan.nexustitanapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Salva um novo aluno no banco
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // Busca todos os alunos
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    // Busca um aluno pelo ID
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    // Deleta um aluno pelo ID
    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    // Atualiza os dados de um aluno existente
    public Aluno atualizar(Long id, Aluno alunoAtualizado) {

        Aluno aluno = buscarPorId(id);

        if (aluno == null) {
            return null;
        }

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setIdade(alunoAtualizado.getIdade());
        aluno.setPeso(alunoAtualizado.getPeso());
        aluno.setAltura(alunoAtualizado.getAltura());
        aluno.setGenero(alunoAtualizado.getGenero());

        return alunoRepository.save(aluno);
    }
}