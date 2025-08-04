package com.ecst.microservicio.model;

import com.ecst.microservicio.enumeration.TipoCuenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long cuentaId;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 30)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;
    
    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteId", nullable = false)
    @JsonIgnoreProperties(value = {"clientes", "hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
    private List<Movimiento> listaMovimientos = new ArrayList<>();
}
