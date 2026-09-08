package com.nexustitan.nexustitanapi.dto;

import com.nexustitan.nexustitanapi.model.DiaSemana;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisponibilidadeResponseDTO {

    private Long id;
    private Long personalId;
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

}
