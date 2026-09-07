package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Column(length = 2000)
    private String descricao;
    @NotNull
    @Min(1)
    private Integer series;
    @NotNull
    @Min(1)
    private Integer repeticoes;
    @NotNull
    @Min(0)
    private Integer tempoDescanso;
    @PositiveOrZero
    private Double carga;
    @ManyToOne(optional = false)
    private TreinoDia treinoDia;

    public Exercicio() {
    }

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

    public Integer getTempoDescanso() {
        return tempoDescanso;
    }

    public Double getCarga() {
        return carga;
    }

    public TreinoDia getTreinoDia() {
        return treinoDia;
    }

    public void setNome(String v) {
        nome = v;
    }

    public void setDescricao(String v) {
        descricao = v;
    }

    public void setSeries(Integer v) {
        series = v;
    }

    public void setRepeticoes(Integer v) {
        repeticoes = v;
    }

    public void setTempoDescanso(Integer v) {
        tempoDescanso = v;
    }

    public void setCarga(Double v) {
        carga = v;
    }

    public void setTreinoDia(TreinoDia v) {
        treinoDia = v;
    }
}
