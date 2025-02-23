package com.hackacode1.dto;


import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPaqueteDTO {
	
	
	private String nombre;
	private String descripcion;
	private Double precio;
	private LocalTime duracion;
}
