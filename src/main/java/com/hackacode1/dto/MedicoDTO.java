package com.hackacode1.dto;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
	private UUID id_medico;
	private String nombre;
	private String apellido;
}
