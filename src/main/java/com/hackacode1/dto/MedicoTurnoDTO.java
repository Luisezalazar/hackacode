package com.hackacode1.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoTurnoDTO {
	
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	private String email;
	private String telefono;
	private String direccion;
	private String especialidadMedica;
	private Double sueldo;
	
	private List<TurnoDTO> turnos;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TurnoDTO{
		private LocalTime horaInicio;
		private LocalTime horaFinal;
	    private LocalTime horaInicioDescanso;
	    private LocalTime horaFinalDescanso;
	    private List<String> horaBloque;
	    private Boolean descanso;
	    private String diaSemana;
	}
}
