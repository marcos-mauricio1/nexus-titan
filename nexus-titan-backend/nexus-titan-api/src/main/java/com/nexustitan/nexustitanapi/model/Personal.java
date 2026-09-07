package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Min(18)
    private Integer idade;
    @NotBlank
    @Column(unique = true)
    private String cpf;
    @NotBlank
    private String genero;
    @NotBlank
    private String tipoPersonal;

    public Personal() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipoPersonal() {
        return tipoPersonal;
    }

    public void setNome(String v) {
        nome = v;
    }

    public void setIdade(Integer v) {
        idade = v;
    }

    public void setCpf(String v) {
        cpf = v;
    }

    public void setGenero(String v) {
        genero = v;
    }

    public void setTipoPersonal(String v) {
        tipoPersonal = v;
    }
}
