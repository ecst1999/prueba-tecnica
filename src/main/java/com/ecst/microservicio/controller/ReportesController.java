package com.ecst.microservicio.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecst.microservicio.dtos.Response;
import com.ecst.microservicio.services.MovimientoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportesController {
    
    private final MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<Response> generarReporte(
        @RequestParam(value = "fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
        @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
        @RequestParam("cliente") Long clienteId
    ){
        return ResponseEntity.ok(movimientoService.generarReporte(fechaInicio, fechaFin, clienteId));
    }
}
