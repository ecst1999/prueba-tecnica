package com.ecst.microservicio.controller;

import com.ecst.microservicio.model.Cliente;
import com.ecst.microservicio.model.Persona;
import com.ecst.microservicio.services.ClienteService;
import com.ecst.microservicio.services.PersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PersonaService personaService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        Persona persona = cliente.getPersona();
        Cliente clienteAGuardar = null;        

        personaService.guardarPersona(persona);

        clienteAGuardar = new Cliente(null, cliente.getContrasena(), cliente.getEstado(), persona, null);

        clienteService.guardarCliente(clienteAGuardar);

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream()
                    .map(e -> e.getField() + " -> " + e.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errores", errores);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("mensaje", "Los datos se guardaron de forma adecuada");

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.obtenerClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClienteId(id);

        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Cliente no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente, BindingResult result) {
        Map<String, Object> response = new HashMap<>();        

        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream()
                    .map(e -> e.getField() + " -> " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Cliente clienteExistente = clienteService.obtenerClienteId(id);
        if (clienteExistente != null) {

            cliente.setClienteId(clienteExistente.getClienteId());
            
            if(cliente.getPersona() != null){
                Cliente actualizado = clienteService.actualizarCliente(cliente);
                response.put("mensaje", "Cliente actualizado correctamente");
                response.put("cliente", actualizado);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                Cliente actualizado = clienteService.actualizarCliente(cliente);
                cliente.setPersona(clienteExistente.getPersona());
                response.put("mensaje", "Cliente actualizado correctamente");
                response.put("cliente", actualizado);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            
            
        } else {
            response.put("mensaje", "Cliente no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteService.obtenerClienteId(id);
        if (cliente != null) {
            clienteService.eliminarCliente(id);            
            response.put("mensaje", "Cliente eliminado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensaje", "Cliente no encontrado");
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
