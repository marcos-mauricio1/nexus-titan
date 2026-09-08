package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.TreinoDia;
import com.nexustitan.nexustitanapi.service.TreinoDiaService;
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
@RequestMapping("/treino-dias")
public class TreinoDiaController {

    private final TreinoDiaService treinoDiaService;

    public TreinoDiaController(TreinoDiaService treinoDiaService) {
        this.treinoDiaService = treinoDiaService;
    }

    // POST
    @PostMapping
    public ResponseEntity<TreinoDia> salvar(@RequestBody TreinoDia treinoDia) {
        return ResponseEntity.ok(treinoDiaService.salvar(treinoDia));
    }

    // GET TODOS
    @GetMapping
    public ResponseEntity<List<TreinoDia>> listarTodos() {
        return ResponseEntity.ok(treinoDiaService.listarTodos());
    }

    // GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<TreinoDia> buscarPorId(@PathVariable Long id) {

        TreinoDia treinoDia = treinoDiaService.buscarPorId(id);

        if (treinoDia == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(treinoDia);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<TreinoDia> atualizar(
            @PathVariable Long id,
            @RequestBody TreinoDia treinoDia) {

        TreinoDia treinoDiaAtualizado =
                treinoDiaService.atualizar(id, treinoDia);

        if (treinoDiaAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(treinoDiaAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        treinoDiaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}