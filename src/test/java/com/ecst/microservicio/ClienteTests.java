package com.ecst.microservicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecst.microservicio.model.Cliente;

@SpringBootTest
public class ClienteTests {
    

    @Test
    void testCliente(){
        Cliente cliente = new Cliente();

        Long idEsperado = 1L;
        String contrasenaEsperado = "12345";
        Boolean estadoEsperado = true;

        String nombreEsperado = "Jose Lema";
        int edadEsperado = 30;
        String identificacionEsperada = "1234567890";
        String direccionEsperada = "Otavalo sn y principal";
        String telefonoEsperado = "0987654321";

        cliente.setClienteId(idEsperado);
        cliente.setContrasena(contrasenaEsperado);
        cliente.setEstado(estadoEsperado);

        cliente.setNombre(nombreEsperado);
        cliente.setEdad(edadEsperado);
        cliente.setIdentificacion(identificacionEsperada);
        cliente.setDireccion(direccionEsperada);
        cliente.setTelefono(telefonoEsperado);

        assertEquals(idEsperado, cliente.getClienteId(), "El ID del cliente no coincide");
        assertEquals(contrasenaEsperado, cliente.getContrasena(), "La contraseña no coincide");
        assertTrue(cliente.getEstado(), "El estado deberia ser verdadero");

        assertEquals(nombreEsperado, cliente.getNombre(), "El nombre heredado no coincide");
        assertEquals(edadEsperado, cliente.getEdad(), "La edad heredada no coincide");
        assertEquals(identificacionEsperada, cliente.getIdentificacion(), "La identificacion no coincide");
        assertNotNull(cliente.toString(), "El cliente no deberia retornar nulo");
    }
}
