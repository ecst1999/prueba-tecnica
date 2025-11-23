package com.ecst.microservicio.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.services.CuentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    
    private final CuentaService cuentaService;

    @PostMapping("/guardar")
    public ResponseEntity<Response> guardarCuenta(@Valid @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.guardarCuenta(cuenta));
    }

    @GetMapping("/")
    public ResponseEntity<Response> listarCuentas() {
        return ResponseEntity.ok(cuentaService.obtenerCuentas());
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Response> obtenerCuentaPorId(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaService.obtenerCuentaNumero(numeroCuenta));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarCuenta(@Valid @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(cuenta));
    }

    @DeleteMapping("/eliminar/{numeroCuenta}")
    public ResponseEntity<Response> eliminarCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaService.eliminarCuenta(numeroCuenta));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepciones(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Ocurrió un error en el sistema");
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
