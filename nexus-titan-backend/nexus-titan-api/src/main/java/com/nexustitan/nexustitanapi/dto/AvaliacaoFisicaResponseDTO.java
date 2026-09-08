package com.nexustitan.nexustitanapi.dto;

import lombok.*;

import java.time.LocalDate;

// Dados que a API devolve depois de salvar/consultar. Já vem com o imc calculado
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvaliacaoFisicaResponseDTO {

    private Long id;
    private Long alunoId;
    private Double peso;
    private Double altura;
    private Double imc;
    private String classificacaoImc;
    private LocalDate dataAvaliacao;
    private String observacoes;

}
