package com.hackacode1.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@GeneratedValue(generator="UUID")
	private UUID codigo_paquete;
	
	@Column(nullable = false)
	private Double precio_paquete;

	@OneToMany
    @JoinColumn(name = "codigo_paquete")  
    private List<Servicio_medico> servicios_medicos;

}