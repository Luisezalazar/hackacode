package com.hackacode1.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoJornadaDTO {
	
		private DayOfWeek diaSemana;
	    private LocalTime horaInicio;
	    private LocalTime horaFinal;
	    private String nombreMedico;
	    private String apellidoMedico;
	    private String especialidadMedica;
}
