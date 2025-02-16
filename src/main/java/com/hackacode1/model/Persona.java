package com.hackacode1.model;

import java.time.LocalDate;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public class Persona {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name="id_persona")
	private UUID id_persona;
	
    @Column(nullable = false)
	private String nombre;
    
    @Column(nullable = false)
	private String apellido;

	@Column(unique = true)
	private String dni;
	
	@Column(nullable = false)
	private LocalDate fechaNac;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String telefono;
	
	@Column(nullable = false)
	private String direccion;

	
}