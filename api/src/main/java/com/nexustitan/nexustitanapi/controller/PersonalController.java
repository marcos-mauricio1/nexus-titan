package com.nexustitan.nexustitanapi.controller;

import com.nexustitan.nexustitanapi.model.Personal;
import com.nexustitan.nexustitanapi.service.PersonalService;
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
@RequestMapping("/personais")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @PostMapping
    public ResponseEntity<Personal> salvar(@RequestBody Personal personal) {
        return ResponseEntity.ok(personalService.salvar(personal));
    }

    @GetMapping
    public ResponseEntity<List<Personal>> listarTodos() {
        return ResponseEntity.ok(personalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> buscarPorId(@PathVariable Long id) {

        Personal personal = personalService.buscarPorId(id);

        if (personal == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> atualizar(
            @PathVariable Long id,
            @RequestBody Personal personal) {

        Personal personalAtualizado =
                personalService.atualizar(id, personal);

        if (personalAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personalAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        personalService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}