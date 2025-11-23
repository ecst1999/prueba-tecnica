package com.ecst.microservicio.model;

import com.ecst.microservicio.enumeration.Genero;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Persona {


    @NotEmpty(message = "El campo nombre es requerido")
    private String nombre;

    @NotNull(message = "El campo genero es requerido")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(name = "edad", length = 3)
    private int edad;

    @Column(name = "identificacion", nullable = false, unique = true, length = 25)
    private String identificacion;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "telefono", length = 25)
    private String telefono;    

}
