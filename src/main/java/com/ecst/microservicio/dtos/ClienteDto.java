package com.ecst.microservicio.dtos;

import lombok.Data;

@Data
public class ClienteDto {
    
    private Long clienteId;
    
    private String contrasena;
        
    private Boolean estado;    

    private String nombre;
    
    private String genero;

    private int edad;

    private String identificacion;

    private String direccion;
    
    private String telefono;
}
