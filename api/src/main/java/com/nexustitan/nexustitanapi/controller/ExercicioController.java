package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.Exercicio;
import com.nexustitan.nexustitanapi.service.ExercicioService;
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
@RequestMapping("/exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    // POST
    @PostMapping
    public ResponseEntity<Exercicio> salvar(@RequestBody Exercicio exercicio) {

        return ResponseEntity.ok(exercicioService.salvar(exercicio));
    }

    // GET TODOS
    @GetMapping
    public ResponseEntity<List<Exercicio>> listarTodos() {

        return ResponseEntity.ok(exercicioService.listarTodos());
    }

    // GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarPorId(@PathVariable Long id) {

        Exercicio exercicio = exercicioService.buscarPorId(id);

        if (exercicio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exercicio);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizar(
            @PathVariable Long id,
            @RequestBody Exercicio exercicio) {

        Exercicio exercicioAtualizado =
                exercicioService.atualizar(id, exercicio);

        if (exercicioAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exercicioAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        exercicioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}