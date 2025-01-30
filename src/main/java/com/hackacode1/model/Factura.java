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
    private LocalDate fecha_emision;

    @Column(nullable = false)
    private LocalDate fecha_vencimiento;

    @Column(nullable = false)
    private Double monto_total;
    
    @Column(nullable = false)
    private String moneda;

    @Column(nullable = false)
    private String forma_pago;

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

	//Un servicio medico
	@ManyToOne
	@JoinColumn(name="codigo_servicio", nullable = true)
	private Servicio_medico servicio;

    
}
