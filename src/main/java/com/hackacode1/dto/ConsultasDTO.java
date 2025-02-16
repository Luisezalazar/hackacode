package com.hackacode1.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ConsultasDTO {
	private LocalDate fechaConsulta;
	private LocalTime horaTurno;
	private Double montoTotal;
	private String pagadoONo;
	
	private String nombrePaciente;
	private String apellidoPaciente;
	
	private String nombreMedico;
	private String apellidoMedico;
	
	private List<ConsultaPaqueteDTO> nombrePaquetes;
}
