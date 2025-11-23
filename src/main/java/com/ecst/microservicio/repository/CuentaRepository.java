package com.ecst.microservicio.repository;

import com.ecst.microservicio.model.Cuenta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    @Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta")
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta")
    void deleteByNumeroCuenta(String numeroCuenta);
}
