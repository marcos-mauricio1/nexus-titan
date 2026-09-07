package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.Aluno;
import com.nexustitan.nexustitanapi.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Aluno x) {
        try {
            return ResponseEntity.ok(service.salvar(x));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Aluno x = service.buscarPorId(id);
        return x == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(x);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno x) {
        try {
            Aluno a = service.atualizar(id, x);
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