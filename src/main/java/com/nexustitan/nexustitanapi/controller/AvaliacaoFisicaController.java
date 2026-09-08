package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.dto.AvaliacaoFisicaRequestDTO;
import com.nexustitan.nexustitanapi.dto.AvaliacaoFisicaResponseDTO;
import com.nexustitan.nexustitanapi.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // é tipo o @Service ele vai avisar a classe que vai receber requisições HTTP e vai devolver com JSON
@RequestMapping("/") // Define o "prefixo" de ebdereço de todos os endpoints dessa classe
// Ou seja, todo método aqui dentro vai começar com http://localhost:8080/api/avaliacoes/...

public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaService service;

    public AvaliacaoFisicaController(AvaliacaoFisicaService service) {
        this.service = service;
    }

    @PostMapping // Esse método é para requisições http do tipo POST
    // (Post = "To enviando dados novos para tu criar algo ai), tu poderia colocar o link de uma página ai, mas vou usar na mesma do request
    public ResponseEntity<AvaliacaoFisicaResponseDTO> criar(@Valid @RequestBody AvaliacaoFisicaRequestDTO dto) {

        // @RequestBody: "pega o JSON que chegou no corpo da requisição
        // e transforma automaticamente num objeto AvaliacaoFisicaRequestDTO".
        // Você nunca escreve código pra "ler" o JSON manualmente — o
        // Spring já faz essa tradução JSON -> objeto Java sozinho.
        //
        // @Valid: "antes de entrar no método, confere se esse objeto
        // respeita as regras de validação que colocamos no DTO"
        // (@NotNull, @Positive...). Se alguma regra for quebrada, o
        // Spring já bloqueia ANTES mesmo do código do método rodar, e
        // aciona o GlobalExceptionHandler que criamos.

        AvaliacaoFisicaResponseDTO resposta = service.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        // ResponseEntity é um "envelope" que carrega, além dos dados,
        // o STATUS HTTP da resposta (o código de 3 dígitos que a
        // internet usa pra dizer se deu certo ou não).
        // HttpStatus.CREATED = código 201, que é o padrão pra "criei
        // um recurso novo com sucesso" (diferente do simples 200 "ok",
        // usado quando você só consulta algo que já existia).
        // .body(resposta) coloca o DTO de resposta dentro desse envelope
        // — ele vira o JSON que a pessoa que chamou a API recebe de volta.

    }

    @GetMapping("/{id}") // Get = "Quero consultar algo desse id, sem alterar nada
    public ResponseEntity<AvaliacaoFisicaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/aluno/{alunoid}")
    public ResponseEntity<List<AvaliacaoFisicaResponseDTO>> listarHistorico (@PathVariable Long alunoId) {
        return ResponseEntity.ok(service.listarHistoricoDoAluno(alunoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
