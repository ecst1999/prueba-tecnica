package com.ecst.microservicio.controller;

import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.model.Movimiento;
import com.ecst.microservicio.services.CuentaService;
import com.ecst.microservicio.services.MovimientoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarMovimiento(@Valid @RequestBody Movimiento movimiento, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        Date fecha = new Date();
        Cuenta cuenta = cuentaService.obtenerCuentaId(movimiento.getCuenta().getCuentaId());

        movimiento.setCuenta(cuenta);
        movimiento.setFecha(fecha);
        movimiento.setSaldo(cuenta.getSaldoInicial());

        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream()
                    .map(e -> e.getField() + " -> " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        movimientoService.guardarMovimiento(movimiento);
        response.put("mensaje", "Movimiento guardado correctamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Movimiento>> listarMovimientos() {
        List<Movimiento> movimientos = movimientoService.obtenerMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMovimientoPorId(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.obtenerMovimientoId(id);

        if (movimiento != null) {
            return new ResponseEntity<>(movimiento, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Movimiento no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMovimiento(@PathVariable Long id, @Valid @RequestBody Movimiento movimiento, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream()
                    .map(e -> e.getField() + " -> " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Movimiento movimientoExistente = movimientoService.obtenerMovimientoId(id);
        if (movimientoExistente != null) {
            movimiento.setMovimientoId(movimientoExistente.getMovimientoId());
            Movimiento actualizado = movimientoService.actualizarMovimiento(movimiento);
            response.put("mensaje", "Movimiento actualizado correctamente");
            response.put("movimiento", actualizado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensaje", "Movimiento no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMovimiento(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Movimiento movimiento = movimientoService.obtenerMovimientoId(id);
        if (movimiento != null) {
            movimientoService.eliminarMovimiento(id);
            response.put("mensaje", "Movimiento eliminado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensaje", "Movimiento no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepciones(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Ocurrió un error en el sistema");
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
