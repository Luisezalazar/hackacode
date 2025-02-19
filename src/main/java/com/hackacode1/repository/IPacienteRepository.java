package com.hackacode1.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hackacode1.model.Consulta_medica;
import com.hackacode1.model.Paciente;
@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, UUID>{
	@Query("SELECT p FROM Paciente p WHERE p.dni = :dni")
    Paciente findByPacienteIdPersona(@Param("dni") String dni);
}
