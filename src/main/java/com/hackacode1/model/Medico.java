package com.hackacode1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Medico extends Persona{
	
	@Column(nullable = false)
	private String especialidadMedica;
	
	@Column(nullable = false)
	private Double sueldo;
	
	@OneToMany
	private List<Turno> listaTurno;
	
	@ManyToMany
	@JoinTable(name = "medico_servicio",
	joinColumns = @JoinColumn(name= "medico_id"),
	inverseJoinColumns = @JoinColumn(name="codigo_servicio"))
	private List<Servicio_medico> serviciosMedicos;
	
}
