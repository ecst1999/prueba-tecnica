package com.ecst.microservicio.repository;

import com.ecst.microservicio.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimiento, Long> {
}
