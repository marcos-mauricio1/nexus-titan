package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.Exercicio;
import com.nexustitan.nexustitanapi.service.ExercicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {
    private final ExercicioService service;

    public ExercicioController(ExercicioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Exercicio x) {
        try {
            return ResponseEntity.ok(service.salvar(x));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public List<Exercicio> listar() {
        return service.listarTodos();
    }

    @GetMapping("/dia/{diaId}")
    public ResponseEntity<?> porDia(@PathVariable Long diaId) {
        return ResponseEntity.ok(service.porDia(diaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Exercicio x = service.buscarPorId(id);
        return x == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(x);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Exercicio x) {
        try {
            Exercicio a = service.atualizar(id, x);
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