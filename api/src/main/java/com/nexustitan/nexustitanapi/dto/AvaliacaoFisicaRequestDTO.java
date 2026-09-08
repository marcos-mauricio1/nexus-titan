package com.nexustitan.nexustitanapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Aqui só vai ter os dados que o cliente pediu
// Dados que vão chegar na API para criar a avaliação. Repare que aqui não tem imc, pq é o service que calcula
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoFisicaRequestDTO {

    @NotNull(message = "alunoId é obrigatório")
    private Long alunoId;

    @NotNull(message = "Peso é obrigatorio")
    @Positive(message = "O peso tem que ser maior que zero") // barra número negativo e zero
    private Double peso;

    @NotNull(message = "Altura é obrigatória")
    @Positive(message = "Altura tem que ser maior que zero")
    private Double altura;

    private LocalDate dataAvaliacao; // Se vier vazio, o Service usa a data de hoje

    private String observacoes;

}
