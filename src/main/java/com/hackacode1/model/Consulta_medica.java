package com.hackacode1.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private UUID idConsultaMedica;

    @Column(nullable = false)
    private LocalDate fechaConsulta;

    @Column(nullable= true)
    private LocalTime horaTurno;
    
    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false) 
    private Turno turno;

    @Column(nullable = false)
    private Double montoTotal;

	@Column(nullable = false)
	private String pagadoONo; 
	
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
}