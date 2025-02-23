package com.hackacode1.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.hackacode1.model.Consulta_medica;
@Repository
public interface IConsulta_medicaRepository extends JpaRepository<Consulta_medica, UUID>{
	@Query("SELECT c FROM Consulta_medica c WHERE c.paciente.dni = :dni")
    List<Consulta_medica> findByPacienteIdPersona(@Param("dni") String dni);
    
    @Query("SELECT c FROM Consulta_medica c WHERE c.medico.id_persona = :idMedico")
    List<Consulta_medica> findByMedicoIdPersona(@Param("idMedico") UUID idMedico);
}
