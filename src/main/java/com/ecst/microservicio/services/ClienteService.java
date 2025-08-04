package com.ecst.microservicio.services;

import com.ecst.microservicio.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente guardarCliente(Cliente cliente);

    Cliente actualizarCliente(Cliente cliente);

    List<Cliente> obtenerClientes();

    Cliente obtenerClienteId(Long clienteId);

    void eliminarCliente(Long clienteId);

}
