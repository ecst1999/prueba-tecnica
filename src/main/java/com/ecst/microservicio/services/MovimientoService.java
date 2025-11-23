package com.ecst.microservicio.services;

import java.time.LocalDate;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.model.Movimiento;


public interface MovimientoService {

    Response guardarMovimiento(Movimiento movimiento);

    Response actualizarMovimiento(Movimiento movimiento);

    Response obtenerMovimientos();

    Response obtenerMovimientoId(Long movimientoId);

    Response eliminarMovimiento(Long movimientoId);

    Response generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);

}
