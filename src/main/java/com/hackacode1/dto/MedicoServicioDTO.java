package com.hackacode1.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MedicoServicioDTO {
	
	private String nombreServicio;
	private Double precio;
	private List<MedicoDTO> nombresMedicos;
}
