package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackacode1.model.Servicio_medico;

public interface IServicio_medicoRepository extends JpaRepository<Servicio_medico,UUID>{
	
}
