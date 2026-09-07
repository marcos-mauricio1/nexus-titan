package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @Min(1)
    private Integer idade;
    @NotNull
    @Positive
    private Double peso;
    @NotNull
    @Positive
    private Double altura;
    @NotBlank
    private String genero;

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdade() {
        return idade;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setNome(String v) {
        nome = v;
    }

    public void setEmail(String v) {
        email = v;
    }

    public void setIdade(Integer v) {
        idade = v;
    }

    public void setPeso(Double v) {
        peso = v;
    }

    public void setAltura(Double v) {
        altura = v;
    }

    public void setGenero(String v) {
        genero = v;
    }

    public Double calcularImc() {
        return peso / (altura * altura);
    }
}
