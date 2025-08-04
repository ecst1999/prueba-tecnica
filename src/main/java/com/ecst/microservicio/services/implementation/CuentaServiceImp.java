package com.ecst.microservicio.services.implementation;

import com.ecst.microservicio.model.Cuenta;
import com.ecst.microservicio.repository.CuentaRepository;
import com.ecst.microservicio.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta obtenerCuentaId(Long cuentaId) {
        return cuentaRepository.findById(cuentaId).orElseThrow();
    }

    @Override
    public void eliminarCuenta(Long cuentaId) {
        cuentaRepository.deleteById(cuentaId);
    }
}
