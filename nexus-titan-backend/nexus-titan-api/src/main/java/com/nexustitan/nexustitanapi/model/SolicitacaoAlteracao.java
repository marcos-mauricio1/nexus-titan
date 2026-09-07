package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class SolicitacaoAlteracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String motivo;
    @Column(length = 2000)
    private String descricao;
    private String status;
    private LocalDate dataSolicitacao;
    @ManyToOne(optional = false)
    private Aluno aluno;
    @ManyToOne(optional = false)
    private Treino treino;
    @ManyToOne(optional = false)
    private TreinoDia treinoDia;
    @ManyToOne(optional = false)
    private Personal personal;

    public SolicitacaoAlteracao() {
    }

    public Long getId() {
        return id;
    }

    public String getMotivo() {
        return motivo;
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

    public TreinoDia getTreinoDia() {
        return treinoDia;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setMotivo(String v) {
        motivo = v;
    }

    public void setDescricao(String v) {
        descricao = v;
    }

    public void setStatus(String v) {
        status = v;
    }

    public void setDataSolicitacao(LocalDate v) {
        dataSolicitacao = v;
    }

    public void setAluno(Aluno v) {
        aluno = v;
    }

    public void setTreino(Treino v) {
        treino = v;
    }

    public void setTreinoDia(TreinoDia v) {
        treinoDia = v;
    }

    public void setPersonal(Personal v) {
        personal = v;
    }
}
