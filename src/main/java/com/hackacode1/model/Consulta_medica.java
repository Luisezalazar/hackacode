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
	private UUID idConsultaMedica;

    @Column(nullable = false)
    private LocalDate fechaTurno;
    
    @Column(nullable= true)
    private LocalTime horaTurno;
 
	@Column(nullable = false)
	private String pagadoONo; 
	
	//Relación con un Paciente 
    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_persona", nullable = false)
    private Paciente paciente;

    //Relación con un Medico 
    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_persona", nullable = false)
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "codigo_servicio", nullable = false)
    private Servicio_medico servicio;
}