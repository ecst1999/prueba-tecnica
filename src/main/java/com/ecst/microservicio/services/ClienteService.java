package com.ecst.microservicio.services;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.model.Cliente;

public interface ClienteService {

    Response guardarCliente(Cliente cliente);

    Response actualizarCliente(Cliente cliente);

    Response obtenerClientes();

    Response obtenerClienteId(Long clienteId);

    Response eliminarCliente(Long clienteId);

}
