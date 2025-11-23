package com.ecst.microservicio.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    
    private int status;
    private String message;

    private ClienteDto cliente;
    private List<ClienteDto> clientes;

    private CuentaDto cuenta;
    private List<CuentaDto> cuentas;    

    private MovimientoDto movimiento;
    private List<MovimientoDto> movimientos;
}
