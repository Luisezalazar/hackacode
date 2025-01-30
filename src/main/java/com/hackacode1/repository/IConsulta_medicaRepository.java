package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackacode1.model.Consulta_medica;

public interface IConsulta_medicaRepository extends JpaRepository<Consulta_medica, UUID>{

}
