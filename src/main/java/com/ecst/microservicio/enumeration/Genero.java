package com.ecst.microservicio.enumeration;

public enum Genero {

    MASCULINO("Masculino"),
    FEMENINO("Femenino");

    private String nombre;

    Genero(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
