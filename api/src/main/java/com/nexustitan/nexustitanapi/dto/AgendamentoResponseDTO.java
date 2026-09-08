package com.nexustitan.nexustitanapi.dto;

import com.nexustitan.nexustitanapi.model.StatusAgendamento;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoResponseDTO {

    private Long id;
    private Long alunoId;
    private Long personalId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendamento status;

}
