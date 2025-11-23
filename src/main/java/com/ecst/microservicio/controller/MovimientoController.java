package com.ecst.microservicio.controller;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.model.Movimiento;
import com.ecst.microservicio.services.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    

    @PostMapping("/guardar")
    public ResponseEntity<Response> guardarMovimiento(@Valid @RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(movimientoService.guardarMovimiento(movimiento));
    }

    @GetMapping("/")
    public ResponseEntity<Response> listarMovimientos() {
        return ResponseEntity.ok(movimientoService.obtenerMovimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerMovimientoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.obtenerMovimientoId(id));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarMovimiento(@Valid @RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(movimientoService.actualizarMovimiento(movimiento));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarMovimiento(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.eliminarMovimiento(id));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepciones(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Ocurrió un error en el sistema");
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
