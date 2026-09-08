package com.nexustitan.nexustitanapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aluno {
    // Atributos privados para proteger os dados da classe.
    // O acesso externo é feito através dos getters e setters.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String email;
    private Integer idade;
    private Double peso;
    private Double altura;
    private String genero;

    public Aluno(){
                // Construtor vazio necessário para o Hibernate
                // Construtor vazio necessário para o Hibernate criar objetos da classe Aluno
                // automaticamente ao buscar informações no banco de dados.
                // O Hibernate precisa de um construtor sem parâmetros para conseguir
                // instanciar a entidade antes de preencher os seus atributos.

    }
    public Aluno(Long id, String nome, String email, Integer idade, Double peso, Double altura, String genero){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
    }

    // GETTERS
    public Long getId(){
        return id;
    }

    public String getNome(){
            return nome;
    }

    public  String getEmail(){
        return email;
    }

    public Integer getIdade(){
        return idade;
    }

    public Double getPeso(){
        return peso;
    }

    public Double getAltura(){
        return altura;
    }

    public String getGenero(){
        return genero;
    }



    //SETERS


    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setIdade(Integer idade){
        this.idade = idade;
    }


    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }







    // metodo

    public Double calcularImc(){
        Double imc = peso / (altura * altura);
        return imc;
    }

}
