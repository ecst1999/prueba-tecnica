package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.exception.NotFoundException;
import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.model.Movimiento;
import com.ecst.microservicio.repository.CuentaRepository;
import com.ecst.microservicio.repository.MovimientosRepository;
import com.ecst.microservicio.services.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MovimientoServiceImp implements MovimientoService {

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Movimiento guardarMovimiento(Movimiento movimiento) {

        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getCuentaId()).orElseThrow(() -> new NotFoundException("No se encontro la cuenta a realizar el movimiento"));

        BigDecimal saldoActual = cuenta.getSaldoInicial();
        BigDecimal valorMovimiento = movimiento.getValor();
        BigDecimal nuevoSaldo = saldoActual.add(valorMovimiento);
        
        if (valorMovimiento.compareTo(BigDecimal.ZERO) < 0 && saldoActual.add(valorMovimiento).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo no disponible");
        }

        movimiento.getCuenta().setSaldoInicial(nuevoSaldo);

        return movimientosRepository.save(movimiento);
    }

    @Override
    public Movimiento actualizarMovimiento(Movimiento movimiento) {
        return movimientosRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> obtenerMovimientos() {
        return movimientosRepository.findAll();
    }

    @Override
    public Movimiento obtenerMovimientoId(Long movimientoId) {
        return movimientosRepository.findById(movimientoId).orElseThrow();
    }

    @Override
    public void eliminarMovimiento(Long movimientoId) {
        movimientosRepository.deleteById(movimientoId);
    }
}
