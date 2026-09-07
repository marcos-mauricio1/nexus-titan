package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.Treino;
import com.nexustitan.nexustitanapi.service.TreinoService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    // POST
    @PostMapping
    public ResponseEntity<Treino> salvar(@RequestBody Treino treino) {

        return ResponseEntity.ok(treinoService.salvar(treino));
    }

    // GET TODOS
    @GetMapping
    public ResponseEntity<List<Treino>> listarTodos() {

        return ResponseEntity.ok(treinoService.listarTodos());
    }

    // GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Treino> buscarPorId(@PathVariable Long id) {

        Treino treino = treinoService.buscarPorId(id);

        if (treino == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(treino);
    }

    // PUT - ATUALIZAR TREINO
    @PutMapping("/{id}")
    public ResponseEntity<Treino> atualizar(
            @PathVariable Long id,
            @RequestBody Treino treino) {

        Treino treinoAtualizado =
                treinoService.atualizar(id, treino);

        if (treinoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(treinoAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        treinoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}