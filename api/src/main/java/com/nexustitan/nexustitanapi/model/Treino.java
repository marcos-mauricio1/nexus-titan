package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String objetivo;
    private String descricao;
    private LocalDate dataCriacao;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Personal personal;

    public Treino() {

    }

    public Treino(Long id, String nome, String objetivo, String descricao,
                  LocalDate dataCriacao, Aluno aluno, Personal personal) {

        this.id = id;
        this.nome = nome;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.aluno = aluno;
        this.personal = personal;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public Aluno getAluno() {
        return aluno;
    }

    public Personal getPersonal() {
        return personal;
    }

    // SETTERS

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}