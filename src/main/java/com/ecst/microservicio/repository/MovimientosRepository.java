package com.ecst.microservicio.repository;

import com.ecst.microservicio.model.Movimiento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m FROM Movimiento m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin AND m.cuenta.cliente.clienteId = :clienteId")
    List<Movimiento> reportePorFechas(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);
}
