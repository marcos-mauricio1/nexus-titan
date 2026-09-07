package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class TreinoDia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String diaSemana;
    @ManyToOne(optional = false)
    private Treino treino;

    public TreinoDia() {
    }

    public Long getId() {
        return id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setDiaSemana(String v) {
        diaSemana = v;
    }

    public void setTreino(Treino v) {
        treino = v;
    }
}
