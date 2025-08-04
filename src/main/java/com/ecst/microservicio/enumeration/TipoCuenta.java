package com.ecst.microservicio.enumeration;

public enum TipoCuenta {

    AHORROS("Ahorros"),
    CORRIENTE("Corriente");

    private String tipo;

    TipoCuenta(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
