package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.dtos.MovimientoDto;
import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.exception.NotFoundException;
import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.model.Movimiento;
import com.ecst.microservicio.repository.CuentaRepository;
import com.ecst.microservicio.repository.MovimientosRepository;
import com.ecst.microservicio.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImp implements MovimientoService {

    
    private final MovimientosRepository movimientosRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public Response guardarMovimiento(Movimiento movimiento) {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta()).orElseThrow(() -> new NotFoundException("No se encontro la cuenta a realizar el movimiento"));        

        BigDecimal saldoActual = cuenta.getSaldoInicial();
        BigDecimal valorMovimiento = movimiento.getValor();
        BigDecimal nuevoSaldo = saldoActual.add(valorMovimiento);
        
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {            
            throw new IllegalArgumentException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);

        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldo(nuevoSaldo);

        movimientosRepository.save(movimiento);
        cuentaRepository.save(cuenta);

        MovimientoDto movimientoDto = mappearDTO(movimiento);

        return Response.builder()
                .status(200)
                .message("Moviento guardado exitosamente")
                .movimiento(movimientoDto)
                .build();
    }

    @Override
    public Response actualizarMovimiento(Movimiento movimiento) {

        Movimiento movimientoExistente = movimientosRepository.findById(movimiento.getMovimientoId()).orElseThrow(() -> new NotFoundException("No se encontro el movimento solicitado"));

        Cuenta cuenta = movimientoExistente.getCuenta();

        if (movimiento.getValor() != null && !movimiento.getValor().equals(movimientoExistente.getValor())) {
            
            BigDecimal valorAnterior = movimientoExistente.getValor();
            BigDecimal valorNuevo = movimiento.getValor();
            BigDecimal diferencia = valorNuevo.subtract(valorAnterior);
            
            BigDecimal nuevoSaldoCuenta = cuenta.getSaldoInicial().add(diferencia);
            
            if (nuevoSaldoCuenta.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("La modificación del movimiento genera un saldo negativo no permitido");
            }
            
            cuenta.setSaldoInicial(nuevoSaldoCuenta);
            movimientoExistente.setValor(valorNuevo);
            movimientoExistente.setSaldo(nuevoSaldoCuenta); 
            
            cuentaRepository.save(cuenta);
        }
        
        if (movimiento.getFecha() != null) {
            movimientoExistente.setFecha(movimiento.getFecha());
        }
        if (movimiento.getTipoMovimiento() != null && !movimiento.getTipoMovimiento().isBlank()) {
            movimientoExistente.setTipoMovimiento(movimiento.getTipoMovimiento());
        }
        
        movimientosRepository.save(movimientoExistente);

        MovimientoDto movimientoDto = mappearDTO(movimientoExistente);
        
        return Response.builder()
                .status(200)
                .message("Movimiento actualizado exitosamente")
                .movimiento(movimientoDto)
                .build();
    }

    @Override
    public Response obtenerMovimientos() {

        List<Movimiento> entidades = movimientosRepository.findAll();

        List<MovimientoDto> dtos = entidades.stream()
            .map(this::mappearDTO)
            .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .movimientos(dtos)
                .build();
    }

    @Override
    public Response obtenerMovimientoId(Long movimientoId) {

        Movimiento entidad = movimientosRepository.findById(movimientoId).orElseThrow(() -> new NotFoundException("No se encontro el movimiento solicitado"));

        MovimientoDto movimiento = mappearDTO(entidad);

        return Response.builder()
                .status(200)
                .movimiento(movimiento)
                .build();
    }

    @Override
    public Response eliminarMovimiento(Long movimientoId) {
        
        movimientosRepository.findById(movimientoId).orElseThrow(() -> new NotFoundException("No se encontro el movimiento solicitado"));

        movimientosRepository.deleteById(movimientoId);

        return Response.builder()
                .status(200)
                .message("Se elimino el movimiento solicitado")
                .build();
    }

    
    @Override
    public Response generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {

        List<Movimiento> entidades = movimientosRepository.reportePorFechas(fechaInicio, fechaFin, clienteId);

        List<MovimientoDto> movimientos = entidades.stream()
                .map(this::mappearDTO)
                .collect(Collectors.toList());
        
        return Response.builder()
                .status(200)
                .movimientos(movimientos)
                .build();
    }

    private MovimientoDto mappearDTO(Movimiento movimiento){
        MovimientoDto dto = new MovimientoDto();


        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setMovimiento(movimiento.getValor());
        dto.setSaldoDisponible(movimiento.getSaldo());

        
        if (movimiento.getCuenta() != null) {
            Cuenta cuenta = movimiento.getCuenta();
            dto.setNumeroCuenta(cuenta.getNumeroCuenta());
            dto.setTipoCuenta(cuenta.getTipoCuenta().toString());
            dto.setEstado(cuenta.getEstado());

            if (cuenta.getCliente() != null) {
                dto.setCliente(cuenta.getCliente().getNombre());
            }
        }
        if (movimiento.getSaldo() != null && movimiento.getValor() != null) {
            BigDecimal saldoInicial = movimiento.getSaldo().subtract(movimiento.getValor());
            dto.setSaldoInicial(saldoInicial);
        }

        return dto;
    }

}
