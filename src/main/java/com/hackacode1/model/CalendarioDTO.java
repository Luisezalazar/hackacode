package com.hackacode1.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarioDTO {

	private LocalDate fecha_consulta;
	private LocalTime hora_consulta;
	private String medico;
	private String paciente;
}
