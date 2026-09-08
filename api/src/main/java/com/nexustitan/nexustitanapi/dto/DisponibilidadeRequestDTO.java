package com.nexustitan.nexustitanapi.dto;

import com.nexustitan.nexustitanapi.model.DiaSemana;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisponibilidadeRequestDTO {

    @NotNull
    private Long personalId;

    @NotNull
    private DiaSemana diaSemana;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFim;

}
