package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.dtos.ClienteDto;
import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.exception.NotFoundException;
import com.ecst.microservicio.model.Cliente;
import com.ecst.microservicio.repository.ClienteRepository;
import com.ecst.microservicio.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImp  implements ClienteService {
    
    private final ClienteRepository clienteRepository;

    @Override
    public Response guardarCliente(Cliente cliente) {
        Cliente clienteGuardado = clienteRepository.save(cliente);

        ClienteDto clienteDto = mapearDto(clienteGuardado);

        return Response.builder()
                .status(200)
                .message("Cliente registrado exitosamente")
                .cliente(clienteDto)
                .build();
    }

    @Override
    public Response actualizarCliente(Cliente cliente) {

        Cliente clienteExistente = clienteRepository.findById(cliente.getClienteId()).orElseThrow(() -> new NotFoundException("No se encontro el cliente solicitado"));
        
        if(cliente.getContrasena() != null && !cliente.getContrasena().isBlank()){
            clienteExistente.setContrasena(cliente.getContrasena());
        }

        if(cliente.getEstado() != null){
            clienteExistente.setEstado(cliente.getEstado());
        }

        if(cliente.getNombre() != null && !cliente.getNombre().isBlank()){
            clienteExistente.setNombre(cliente.getNombre());
        }

        if(cliente.getGenero() != null){
            clienteExistente.setGenero(cliente.getGenero());
        }

        if(cliente.getEdad() != 0){
            clienteExistente.setEdad(cliente.getEdad());
        }

        if(cliente.getIdentificacion() != null && !cliente.getIdentificacion().isBlank()){
            clienteExistente.setIdentificacion(cliente.getIdentificacion());
        }

        if(cliente.getDireccion() != null && !cliente.getDireccion().isBlank()){
            clienteExistente.setDireccion(cliente.getDireccion());
        }

        if(cliente.getTelefono() != null && !cliente.getTelefono().isBlank()){
            clienteExistente.setTelefono(cliente.getTelefono());
        }

        clienteRepository.save(clienteExistente);

        ClienteDto clienteDto = mapearDto(clienteExistente);

        return Response.builder()
                    .status(200)
                    .message("Cliente actualizado exitosamente")
                    .cliente(clienteDto)
                    .build();
    }

    @Override
    public Response obtenerClientes() {

        List<Cliente> entidades = clienteRepository.findAll();

        List<ClienteDto> clientes = entidades.stream()
                .map(this::mapearDto)
                .collect(Collectors.toList());

        return Response.builder()
                    .status(200)
                    .clientes(clientes)
                    .build();
    }

    @Override
    public Response obtenerClienteId(Long clienteId) {
        
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException("No se encontro el cliente solicitado"));

        ClienteDto clienteDto = mapearDto(cliente);
        
        return Response.builder()
                .status(200)
                .cliente(clienteDto)
                .build();
    }

    @Override
    public Response eliminarCliente(Long clienteId) {

        clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException("No se encontro el cliente solicitado"));

        clienteRepository.deleteById(clienteId);
        
        return Response.builder()
                .status(200)
                .message("El cliente fue eliminado exitosamente")
                .build();
    }

    private ClienteDto mapearDto(Cliente cliente){
        ClienteDto dto = new ClienteDto();
        
        dto.setClienteId(cliente.getClienteId());
        dto.setContrasena(cliente.getContrasena());
        dto.setEstado(cliente.getEstado());
                
        dto.setNombre(cliente.getNombre());
        
        if (cliente.getGenero() != null) {
            dto.setGenero(cliente.getGenero().toString()); 
        }

        dto.setEdad(cliente.getEdad());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());

        return dto;
    }
}
