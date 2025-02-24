package com.hackacode1.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialDTO {
	




	public HistorialDTO(String nombre, String apellido, String dni, String especialidadMedica2, LocalDate fechaTurno2,
			LocalTime horaTurno2, String nombre2, String apellido2, String genero2, LocalDate fechaNac2, String dni2,
			String telefono2, String email2, String direccion2, String pagadoONo2, String nombre3, String descripcion2,
			Double precio2, LocalTime duracion2) {
		// TODO Auto-generated constructor stub
	}




	private String pagadoONo;
	
	
    private String nombreMedico;
    private String apellidoMedico;
    private String especialidadMedica;
    private String dniMedico;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    
    private String nombrePaciente;
    private String apellidoPaciente;
    private String genero;
    private String dniPaciente;
    private String fechaNac;
    private String telefono;
    private String email;
    private String direccion;
    
    private String nombreServicio;
    private String descripcion;
    private Double precio;
    private LocalTime Duracion;
}
