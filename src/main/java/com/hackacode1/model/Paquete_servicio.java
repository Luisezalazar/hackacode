package com.hackacode1.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Paquete_servicio {
	
	@Id
	private UUID codigo_paquete;
	
	@Column(nullable = false)
	private Double precioPaquete;
	
	@Column(nullable = false)
	private LocalDate fechaCreacion;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Consulta_medica> consultas;
    
}