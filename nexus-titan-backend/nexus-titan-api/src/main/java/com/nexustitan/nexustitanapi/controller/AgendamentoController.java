package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.dto.AgendamentoRequestDTO;
import com.nexustitan.nexustitanapi.dto.AgendamentoResponseDTO;
import com.nexustitan.nexustitanapi.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController (AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar (@Valid @RequestBody AgendamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PatchMapping("/{id}/cancelar")
    // PATCH = "quero alterar só UMA PARTE de algo que já existe"
    // (diferente do PUT, que normalmente substitui o objeto inteiro).
    // Aqui faz sentido porque cancelar só muda o campo "status" —
    // não faz sentido reenviar todos os outros dados do agendamento
    // só pra cancelar ele.
    //
    // Repare também algo no endereço: "/{id}/cancelar" é uma ação
    // específica, não um recurso genérico. Isso é uma convenção comum
    // quando uma operação não é um simples "criar/editar/apagar",
    // e sim uma AÇÃO de negócio com nome próprio.
    public ResponseEntity<AgendamentoResponseDTO> cancelar (@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorAluno (@PathVariable Long alunoId) {
        return ResponseEntity.ok(service.listarPorAluno(alunoId));
    }

    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorPersonal (@PathVariable Long personalId) {
        return ResponseEntity.ok(service.listarPorPersonal(personalId));
    }

}
