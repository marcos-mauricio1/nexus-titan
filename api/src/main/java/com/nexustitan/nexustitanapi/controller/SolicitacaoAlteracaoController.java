package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.SolicitacaoAlteracao;
import com.nexustitan.nexustitanapi.service.SolicitacaoAlteracaoService;

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
@RequestMapping("/solicitacoes-alteracao")
public class SolicitacaoAlteracaoController {

    private final SolicitacaoAlteracaoService solicitacaoAlteracaoService;

    public SolicitacaoAlteracaoController(
            SolicitacaoAlteracaoService solicitacaoAlteracaoService) {

        this.solicitacaoAlteracaoService = solicitacaoAlteracaoService;
    }

    // POST
    @PostMapping
    public ResponseEntity<SolicitacaoAlteracao> salvar(
            @RequestBody SolicitacaoAlteracao solicitacao) {

        return ResponseEntity.ok(
                solicitacaoAlteracaoService.salvar(solicitacao)
        );
    }

    // GET TODOS
    @GetMapping
    public ResponseEntity<List<SolicitacaoAlteracao>> listarTodos() {

        return ResponseEntity.ok(
                solicitacaoAlteracaoService.listarTodos()
        );
    }

    // GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoAlteracao> buscarPorId(
            @PathVariable Long id) {

        SolicitacaoAlteracao solicitacao =
                solicitacaoAlteracaoService.buscarPorId(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solicitacao);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoAlteracao> atualizar(
            @PathVariable Long id,
            @RequestBody SolicitacaoAlteracao solicitacao) {

        SolicitacaoAlteracao solicitacaoAtualizada =
                solicitacaoAlteracaoService.atualizar(id, solicitacao);

        if (solicitacaoAtualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solicitacaoAtualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        solicitacaoAlteracaoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SolicitacaoAlteracao> atualizarStatus(
            @PathVariable Long id,
            @RequestBody SolicitacaoAlteracao solicitacao) {

        SolicitacaoAlteracao solicitacaoAtualizada =
                solicitacaoAlteracaoService.atualizarStatus(
                        id,
                        solicitacao.getStatus()
                );

        if (solicitacaoAtualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solicitacaoAtualizada);
    }
}