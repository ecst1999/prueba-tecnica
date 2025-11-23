package com.ecst.microservicio.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class MovimientoDto {    

    private LocalDate fecha;
        
    private String tipoMovimiento;    
    
    private String numeroCuenta;
    
    private String cliente; 
    
    private String tipoCuenta;    

    private BigDecimal saldoInicial; 

    private boolean estado;

    private BigDecimal movimiento; 

    private BigDecimal saldoDisponible; 
}
