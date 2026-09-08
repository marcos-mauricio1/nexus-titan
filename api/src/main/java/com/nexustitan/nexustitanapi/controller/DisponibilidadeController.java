package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.dto.DisponibilidadeRequestDTO;
import com.nexustitan.nexustitanapi.dto.DisponibilidadeResponseDTO;
import com.nexustitan.nexustitanapi.model.DiaSemana;
import com.nexustitan.nexustitanapi.model.Disponibilidade;
import com.nexustitan.nexustitanapi.service.DisponibilidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidade")
public class DisponibilidadeController {

    private final DisponibilidadeService service;

    public DisponibilidadeController (DisponibilidadeService service) {this.service = service;}

    @PostMapping
    public ResponseEntity<DisponibilidadeResponseDTO> cadastrar(@Valid @RequestBody DisponibilidadeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @PutMapping("/{id}") // Quero atualizar algo desse id
    public ResponseEntity<DisponibilidadeResponseDTO> editar (@PathVariable Long id, @Valid @RequestBody DisponibilidadeRequestDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/personal/{personalid}")
    public ResponseEntity<List<DisponibilidadeResponseDTO>> listarPorPersonal (@PathVariable Long personalId) {
        return ResponseEntity.ok(service.listarPorPersonal(personalId));
    }

    @GetMapping("/personal/{personalId}/dia/{diaSemana}")
    public ResponseEntity<List<DisponibilidadeResponseDTO>> consultarPorDia(@PathVariable Long personalId, @PathVariable DiaSemana diaSemana) {
        return ResponseEntity.ok(service.consultarPorPersonalEDia(personalId,diaSemana));
    }

}
