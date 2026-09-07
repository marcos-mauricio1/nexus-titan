package com.nexustitan.nexustitanapi.controller;
import org.springframework.web.bind.annotation.PutMapping;
import com.nexustitan.nexustitanapi.model.Aluno;
import com.nexustitan.nexustitanapi.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.salvar(aluno));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {

        Aluno aluno = alunoService.buscarPorId(id);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        alunoService.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable Long id,
            @RequestBody Aluno aluno) {

        Aluno alunoAtualizado = alunoService.atualizar(id, aluno);

        if (alunoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alunoAtualizado);
    }

}