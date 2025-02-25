package com.hackacode1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GraficoGeneroDTO {
	private String sexo;
	private Integer hombre;
	private Integer mujer;
	private Integer intersexual;
}
