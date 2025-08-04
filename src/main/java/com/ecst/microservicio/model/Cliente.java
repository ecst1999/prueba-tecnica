package com.ecst.microservicio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "contrasena", nullable = false, length = 35)
    private String contrasena;
    
    @Column(name = "estado")
    private Boolean estado;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = {"persona", "hibernateLazyInitializer", "handler"})    
    private Persona persona;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonIgnoreProperties(value = {"listaCuentas", "hibernateLazyInitializer", "handler"})
    private List<Cuenta> listaCuentas = new ArrayList<>();
}
