package com.hackacode1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Medico extends Persona{
	
	@Column(nullable = false)
	private String especialidad_medica;
	
	@Column(nullable = false)
	private Double sueldo;
	
	
}
