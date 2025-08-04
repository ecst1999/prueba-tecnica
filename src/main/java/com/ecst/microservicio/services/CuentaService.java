package com.ecst.microservicio.services;

import com.ecst.microservicio.model.Cuenta;

import java.util.List;

public interface CuentaService {

    Cuenta guardarCuenta(Cuenta cuenta);

    Cuenta actualizarCuenta(Cuenta cuenta);

    List<Cuenta> obtenerCuentas();

    Cuenta obtenerCuentaId(Long cuentaId);

    void eliminarCuenta(Long cuentaId);


}
