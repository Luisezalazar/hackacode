package com.hackacode1.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Factura {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID numeroFactura;

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private Double montoTotal;
    
    @Column(nullable = false)
    private String moneda;

    @Column(nullable = false)
    private String formaPago;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private Double descuento;

    @Column(nullable = false)
    private Double iva;
    
    //Un paquete de servicios
	@ManyToOne
	@JoinColumn(name="codigo_paquete", nullable = true)
	private Paquete_servicio paquete;


    
}
