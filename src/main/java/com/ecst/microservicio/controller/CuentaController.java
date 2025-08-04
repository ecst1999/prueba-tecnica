package com.ecst.microservicio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecst.microservicio.model.Cliente;
import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.services.ClienteService;
import com.ecst.microservicio.services.CuentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCuenta(@Valid @RequestBody Cuenta cuenta, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(e -> e.getField() + " -> " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = cuenta.getCliente();
        if (cliente != null && cliente.getClienteId() != null) {
            cliente = clienteService.obtenerClienteId(cliente.getClienteId());
            cuenta.setCliente(cliente);
        }

        cuentaService.guardarCuenta(cuenta);
        response.put("mensaje", "Cuenta guardada correctamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaId(id);

        if (cuenta != null) {
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Cuenta no encontrada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCuenta(@PathVariable Long id, @Valid @RequestBody Cuenta cuenta, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(e -> e.getField() + " -> " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Cuenta cuentaExistente = cuentaService.obtenerCuentaId(id);
        if (cuentaExistente != null) {
            cuenta.setCuentaId(cuentaExistente.getCuentaId());
            if (cuenta.getCliente() != null) {
                cuenta.setCliente(clienteService.obtenerClienteId(cuenta.getCliente().getClienteId()));
            } else {
                cuenta.setCliente(cuentaExistente.getCliente());
            }
            cuentaService.actualizarCuenta(cuenta);
            response.put("mensaje", "Cuenta actualizada correctamente");            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensaje", "Cuenta no encontrada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cuenta cuenta = cuentaService.obtenerCuentaId(id);
        if (cuenta != null) {
            cuentaService.eliminarCuenta(id);
            response.put("mensaje", "Cuenta eliminada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensaje", "Cuenta no encontrada");
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
