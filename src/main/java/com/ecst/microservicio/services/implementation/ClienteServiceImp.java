package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.exception.NotFoundException;
import com.ecst.microservicio.model.Cliente;
import com.ecst.microservicio.repository.ClienteRepository;
import com.ecst.microservicio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImp  implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClienteId(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException("No se encontro el cliente con el id: " + clienteId));
    }

    @Override
    public void eliminarCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
