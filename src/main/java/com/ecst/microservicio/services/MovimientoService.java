package com.ecst.microservicio.services;

import com.ecst.microservicio.model.Movimiento;

import java.util.List;

public interface MovimientoService {

    Movimiento guardarMovimiento(Movimiento movimiento);

    Movimiento actualizarMovimiento(Movimiento movimiento);

    List<Movimiento> obtenerMovimientos();

    Movimiento obtenerMovimientoId(Long movimientoId);

    void eliminarMovimiento(Long movimientoId);

}
