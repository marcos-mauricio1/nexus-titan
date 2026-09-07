package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.SolicitacaoAlteracao;
import com.nexustitan.nexustitanapi.service.SolicitacaoAlteracaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/solicitacoes-alteracao")
public class SolicitacaoAlteracaoController {
    private final SolicitacaoAlteracaoService service;

    public SolicitacaoAlteracaoController(SolicitacaoAlteracaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody SolicitacaoAlteracao x) {
        try {
            return ResponseEntity.ok(service.salvar(x));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public List<SolicitacaoAlteracao> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        SolicitacaoAlteracao x = service.buscarPorId(id);
        return x == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(x);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody SolicitacaoAlteracao x) {
        try {
            SolicitacaoAlteracao a = service.atualizar(id, x);
            return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> status(@PathVariable Long id, @RequestBody SolicitacaoAlteracao x) {
        SolicitacaoAlteracao a = service.atualizarStatus(id, x.getStatus());
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}