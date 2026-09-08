package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "disponibilidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @Enumerated(EnumType.STRING) // Essa anotação fala "quando for salvar no banco de dados, salva o texto da classe enum"
    @Column(name = "dia_semana",nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio",nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim",nullable = false)
    private LocalTime horaFim;

    public boolean contemIntervalo(LocalTime inicio, LocalTime fim) {
        return !inicio.isBefore(this.horaInicio) && !fim.isAfter(horaFim); // Limita o intervalo para ser depois de ter começado o treino e ante de ter terminado
    }

}
