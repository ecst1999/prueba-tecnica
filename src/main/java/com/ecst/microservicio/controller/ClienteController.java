package com.ecst.microservicio.controller;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.model.Cliente;
import com.ecst.microservicio.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    
    private final ClienteService clienteService;        

    @PostMapping("/guardar")
    public ResponseEntity<Response> guardarCliente(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.guardarCliente(cliente));
    }

    @GetMapping("/")
    public ResponseEntity<Response> listarClientes() {        
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClienteId(id));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Response> actualizarCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(cliente));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.eliminarCliente(id));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepciones(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Ocurrió un error en el sistema");
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
