package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.model.Persona;
import com.ecst.microservicio.repository.PersonaRepository;
import com.ecst.microservicio.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> obtenerPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Persona obtenerPersonaId(Long personaId) {
        return personaRepository.findById(personaId).orElseThrow();
    }

    @Override
    public void eliminarPersona(Long personaId) {
        personaRepository.deleteById(personaId);
    }
}
