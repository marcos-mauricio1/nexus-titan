package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Integer series;
    private Integer repeticoes;
    private Double carga;
    private Integer tempoDescanso;

    @ManyToOne
    private TreinoDia treinoDia;

    public Exercicio() {

    }

    public Exercicio(Long id, String nome, String descricao,
                     Integer series, Integer repeticoes,
                     Double carga, Integer tempoDescanso,
                     TreinoDia treinoDia) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.tempoDescanso = tempoDescanso;
        this.treinoDia = treinoDia;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getSeries() {
        return series;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public Double getCarga() {
        return carga;
    }

    public Integer getTempoDescanso() {
        return tempoDescanso;
    }

    public TreinoDia getTreinoDia() {
        return treinoDia;
    }

    // SETTERS

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public void setCarga(Double carga) {
        this.carga = carga;
    }

    public void setTempoDescanso(Integer tempoDescanso) {
        this.tempoDescanso = tempoDescanso;
    }

    public void setTreinoDia(TreinoDia treinoDia) {
        this.treinoDia = treinoDia;
    }
}