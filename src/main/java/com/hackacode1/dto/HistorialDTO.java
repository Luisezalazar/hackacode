package com.hackacode1.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialDTO {
	private LocalDate fechaConsulta;
    private Double montoTotal;
    private String pagadoONo;
    private String nombreMedico;
    private String nombrePaciente;
    private List<ConsultaPaqueteDTO> nombrePaquetes;
}
