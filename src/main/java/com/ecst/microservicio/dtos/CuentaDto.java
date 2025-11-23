package com.ecst.microservicio.dtos;

import java.math.BigDecimal;
import com.ecst.microservicio.enumeration.TipoCuenta;
import lombok.Data;

@Data
public class CuentaDto {
    
    private String numeroCuenta;
    
    private TipoCuenta tipoCuenta;
    
    private BigDecimal saldoInicial;
        
    private Boolean estado;
    
    private String nombreCliente;
    
}
