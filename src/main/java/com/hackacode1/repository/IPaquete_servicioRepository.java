package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackacode1.model.Paquete_servicio;

public interface IPaquete_servicioRepository extends JpaRepository<Paquete_servicio, UUID>{

}
