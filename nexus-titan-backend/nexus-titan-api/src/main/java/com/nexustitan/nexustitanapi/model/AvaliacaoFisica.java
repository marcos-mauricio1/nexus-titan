package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity // Fala para o Spring que "Essa classe representa uma tabela real no banco de dados"
@Table(name = "avaliacao_fisica") // Define o nome exato da tabela no MYSQL
@Getter // Gera automaticamente todos os metódos getters
@Setter // Gera automaticante todos os metódos setters
@NoArgsConstructor // Gera um "construtor vazio": new AvaliacaoFisica() sem passar nada.
// o JPA/Hibernate exige que toda entidade tenha um construtor vazio
@AllArgsConstructor // Gera um construtor que recebe todos os campo de uma vez em ordem que eles aparecem na classe
// new AvaliacaoFisica(id,alunoId,peso,altura,imc,dataAvaliacao,observacoes)
@Builder // permite criar um objeto de um jeito mais legivel e seguro
public class AvaliacaoFisica {

    @Id // Marca essa coluna como chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fala pro banco de dados gerar os números automaticamente em orde crescente(1,2,3,4,...)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //  diz: "MUITAS avaliações podem apontar pra UM aluno". (Um aluno pode ter várias avaliações ao longo do tempo, mas cada
    // avaliação pertence a um único aluno — daí o nome "muitos-pra-um".)
    //
    //  fetch = FetchType.LAZY quer dizer: "só busca os dados do Aluno
    //    // no banco QUANDO eu realmente pedir (ex: avaliacao.getAluno().getNome())".
    @JoinColumn(name = "aluno_id", nullable = false) // o "name" é para nomear a coluna do jeito que o programador quer
    // "nullable = false" fala para o banco que essa coluna é obrigatória, o banco se recusa a salvar uma linha sem esse valor preenchido
    private Aluno aluno; //Chave estrangeira

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private Double imc; // Esse campo vai ser preenchido pelo método calcularIMC()

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao;

    @Column(length = 500)
    private String observacoes;

    public void calcularImc() {
        if(peso > 0 && altura > 0) {
            this.imc = arredondar(peso/(altura*altura));
        }
    }

    public double arredondar(double valor) {
        return Math.round(valor*100)/100;
    }

}
