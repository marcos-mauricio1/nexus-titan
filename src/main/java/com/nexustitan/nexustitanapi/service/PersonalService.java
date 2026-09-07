package com.nexustitan.nexustitanapi.service;

import com.nexustitan.nexustitanapi.model.Personal;
import com.nexustitan.nexustitanapi.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public Personal salvar(Personal personal) {
        return personalRepository.save(personal);
    }

    public List<Personal> listarTodos() {
        return personalRepository.findAll();
    }

    public Personal buscarPorId(Long id) {
        return personalRepository.findById(id).orElse(null);
    }

    // Atualizar personal
    public Personal atualizar(Long id, Personal personalAtualizado) {

        Personal personal = buscarPorId(id);

        if (personal == null) {
            return null;
        }

        personal.setNome(personalAtualizado.getNome());
        personal.setIdade(personalAtualizado.getIdade());
        personal.setCpf(personalAtualizado.getCpf());
        personal.setGenero(personalAtualizado.getGenero());
        personal.setTipoPersonal(personalAtualizado.getTipoPersonal());

        return personalRepository.save(personal);
    }

    public void deletar(Long id) {
        personalRepository.deleteById(id);
    }
}