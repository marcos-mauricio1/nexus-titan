package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String tipoTreino;
    @NotBlank
    private String objetivo;
    @Column(length = 2000)
    private String descricao;
    private LocalDate dataCriacao;
    private Boolean ativo = true;
    @ManyToOne(optional = false)
    private Aluno aluno;
    @ManyToOne(optional = false)
    private Personal personal;

    public Treino() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoTreino() {
        return tipoTreino;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setNome(String v) {
        nome = v;
    }

    public void setTipoTreino(String v) {
        tipoTreino = v;
    }

    public void setObjetivo(String v) {
        objetivo = v;
    }

    public void setDescricao(String v) {
        descricao = v;
    }

    public void setDataCriacao(LocalDate v) {
        dataCriacao = v;
    }

    public void setAtivo(Boolean v) {
        ativo = v;
    }

    public void setAluno(Aluno v) {
        aluno = v;
    }

    public void setPersonal(Personal v) {
        personal = v;
    }
}
