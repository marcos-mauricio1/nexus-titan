package com.nexustitan.nexustitanapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @Column(name = "data_hora_inicio",nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim",nullable = false)
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status;

    @Column(name = "criado_em") // Este campo vai ser preenchido automaticamente pelo método abaixo
    private LocalDateTime criadoEm;

    @PrePersist // Essa anotação diz "execute este método automaticamente, um instante ANTES de salvar este objeto no banco pela PRIMEIRA vez
    public void prePersist() {
        this.criadoEm = LocalDateTime.now(); // Registra a hora exata que o agendamento foi criado

        if (this.status == null) { // Se quem criou o agendamento deixar nulo o sistema vai definir automaticamente como agendado
            this.status = StatusAgendamento.AGENDADO;
        }

    }

}
