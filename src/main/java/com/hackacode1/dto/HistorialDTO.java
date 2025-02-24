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
    private LocalDate fechaNac;
    private String telefono;
    private String email;
    private String direccion;
    
    private String nombreServicio;
    private String descripcion;
    private Double precio;
    private LocalTime Duracion;
}
