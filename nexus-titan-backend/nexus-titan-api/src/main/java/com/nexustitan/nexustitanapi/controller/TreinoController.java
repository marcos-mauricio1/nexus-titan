package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.Treino;
import com.nexustitan.nexustitanapi.service.TreinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/treinos")
public class TreinoController {
    private final TreinoService service;

    public TreinoController(TreinoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Treino x) {
        try {
            return ResponseEntity.ok(service.salvar(x));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public List<Treino> listar() {
        return service.listarTodos();
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<?> porAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(service.listarPorAluno(alunoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Treino x = service.buscarPorId(id);
        return x == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(x);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Treino x) {
        try {
            Treino a = service.atualizar(id, x);
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