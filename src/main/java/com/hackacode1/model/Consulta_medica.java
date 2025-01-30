package com.hackacode1.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Consulta_medica {
	 
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id_consulta_medica;

	@Column(nullable = false)
	private LocalDate fecha_consulta;
	
	@Column(nullable = false)
	private LocalTime hora_consulta;
	
	@Column(nullable = false)
	private Double monto_total;
	
	@Column(nullable = false)
	private String pagado_o_no;
	
	
	
	 // Relación con un Paciente 
    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_persona", nullable = false)
    private Paciente paciente;

    // Relación con un Medico 
    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_persona", nullable = false)
    private Medico medico;
	
	//Un paquete de servicios
	@ManyToOne
	@JoinColumn(name="codigo_paquete", nullable = true)
	private Paquete_servicio paquete;

	//Un servicio medico
	@ManyToOne
	@JoinColumn(name="codigo_servicio", nullable = true)
	private Servicio_medico servicio;
}