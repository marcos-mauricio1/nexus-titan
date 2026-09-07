package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TreinoDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diaSemana;

    @ManyToOne
    private Treino treino;

    public TreinoDia() {

    }

    public TreinoDia(Long id, String diaSemana, Treino treino) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.treino = treino;
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public Treino getTreino() {
        return treino;
    }

    // SETTERS

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
}