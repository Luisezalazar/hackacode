package com.hackacode1.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Turno {
	
	@Id
	@GeneratedValue(generator="UUID")
	private UUID id_turno;
	
	@Column(nullable=false)
	private LocalTime horaInicio;
	@Column(nullable=false)
	private LocalTime horaFinal;
	
	@Column(nullable=false)
	private LocalTime horaInicioDescanso;
	@Column(nullable=false)
	private LocalTime horaFinalDescanso;
	
	@Column(nullable = true)
	private String ultimaHoraOcupada;
	
	@Column(nullable = false)
	private List<String> horaBloque;
	
	private Boolean descanso;
	private DayOfWeek diaSemana;
	
}
