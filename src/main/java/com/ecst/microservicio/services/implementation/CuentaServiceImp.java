package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.dtos.CuentaDto;
import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.exception.NotFoundException;
import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.repository.CuentaRepository;
import com.ecst.microservicio.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaServiceImp implements CuentaService {
    
    private final CuentaRepository cuentaRepository;

    @Override
    public Response guardarCuenta(Cuenta cuenta) {
        
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        CuentaDto cuentaDto = mapearDto(cuentaGuardada);
        
        return Response.builder()
                .status(200)
                .message("Cuenta creada exitosamente")
                .cuenta(cuentaDto)
                .build();
    }

    @Override
    public Response actualizarCuenta(Cuenta cuenta) {

        Cuenta cuentaExistente = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).orElseThrow(() -> new NotFoundException("No se encontro la cuenta solicitada"));

        if(cuenta.getNumeroCuenta() != null && !cuenta.getNumeroCuenta().isBlank()){
            cuentaExistente.setNumeroCuenta(cuenta.getNumeroCuenta());
        }

        if(cuenta.getTipoCuenta() != null){
            cuentaExistente.setTipoCuenta(cuenta.getTipoCuenta());
        }

        if(cuenta.getSaldoInicial() != null){
            cuentaExistente.setSaldoInicial(cuenta.getSaldoInicial());
        }

        if(cuenta.getEstado() != null){
            cuentaExistente.setEstado(cuenta.getEstado());
        }

        cuentaRepository.save(cuentaExistente);

        CuentaDto cuentaDto = mapearDto(cuentaExistente);
        
        return Response.builder()
                .status(200)
                .message("Cuenta actualizada exitosamente")
                .cuenta(cuentaDto)
                .build();
    }

    @Override
    public Response obtenerCuentas() {

        List<Cuenta> entidades = cuentaRepository.findAll();

        List<CuentaDto> cuentas = entidades.stream()
                .map(this::mapearDto)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .cuentas(cuentas)
                .build();
    }

    @Override
    public Response obtenerCuentaNumero(String numeroCuenta) {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(() -> new NotFoundException("No se encontro la cuenta solicitada"));

        CuentaDto cuentaDto = mapearDto(cuenta);

        return Response.builder()
                .status(200)
                .cuenta(cuentaDto)
                .build();
    }

    @Override
    public Response eliminarCuenta(String numeroCuenta) {

        cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(() -> new NotFoundException("No se encontro la cuenta solicitada"));
        
        cuentaRepository.deleteByNumeroCuenta(numeroCuenta);

        return Response.builder()
                .status(200)
                .message("Cuenta eliminada exitosamente")
                .build();
    }

    private CuentaDto mapearDto(Cuenta cuenta){
        CuentaDto dto = new CuentaDto();

        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado()); 
        
        
        if (cuenta.getCliente() != null) {
            dto.setNombreCliente(cuenta.getCliente().getNombre());
        }

        return dto;
    }
}
