package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.TreinoDia;
import com.nexustitan.nexustitanapi.service.TreinoDiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/treino-dias")
public class TreinoDiaController {
    private final TreinoDiaService service;

    public TreinoDiaController(TreinoDiaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody TreinoDia x) {
        try {
            return ResponseEntity.ok(service.salvar(x));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public List<TreinoDia> listar() {
        return service.listarTodos();
    }

    @GetMapping("/treino/{treinoId}")
    public ResponseEntity<?> porTreino(@PathVariable Long treinoId) {
        return ResponseEntity.ok(service.porTreino(treinoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        TreinoDia x = service.buscarPorId(id);
        return x == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(x);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody TreinoDia x) {
        try {
            TreinoDia a = service.atualizar(id, x);
            return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}