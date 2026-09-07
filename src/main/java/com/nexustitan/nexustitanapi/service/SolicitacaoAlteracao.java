package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class SolicitacaoAlteracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String status;
    private LocalDate dataSolicitacao;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Treino treino;

    public SolicitacaoAlteracao() {

    }

    public SolicitacaoAlteracao(Long id, String descricao, String status,
                                LocalDate dataSolicitacao, Aluno aluno, Treino treino) {

        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.dataSolicitacao = dataSolicitacao;
        this.aluno = aluno;
        this.treino = treino;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Treino getTreino() {
        return treino;
    }

    // SETTERS

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
}