package com.ecst.microservicio.services;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.model.Cuenta;

public interface CuentaService {

    Response guardarCuenta(Cuenta cuenta);

    Response actualizarCuenta(Cuenta cuenta);

    Response obtenerCuentas();

    Response obtenerCuentaNumero(String numeroCuenta);

    Response eliminarCuenta(String numeroCuenta);


}
