package com.ecst.microservicio.services;

import com.ecst.microservicio.model.Persona;

import java.util.List;

public interface PersonaService {

    Persona guardarPersona(Persona persona);

    Persona actualizarPersona(Persona persona);

    List<Persona> obtenerPersonas();

    Persona obtenerPersonaId(Long personaId);

    void eliminarPersona(Long personaId);

}
