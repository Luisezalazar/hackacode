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

@Entity
public class Servicio_consulta {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID idServicioConsulta;

    @ManyToOne
    @JoinColumn(name = "id_consulta_medica", nullable = false)
    private Consulta_medica consultaMedica;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", nullable = false)
    private Servicio_medico servicio; // Relación con la entidad Servicio

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id_persona", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private LocalDate fechaServicio;

    @Column(nullable = true)
    private LocalTime horaTurno;
}