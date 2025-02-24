package com.hackacode1.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hackacode1.model.Consulta_medica;
import com.hackacode1.model.Paquete_servicio;
@Repository
public interface IPaquete_servicioRepository extends JpaRepository<Paquete_servicio, UUID>{
	@Query("SELECT p FROM Paquete_servicio p WHERE p.dniSolicitante = :dniSolicitante")
    List<Paquete_servicio> findByDniSolicitante(@Param("dniSolicitante") String dniSolicitante);
}
