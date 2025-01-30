package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackacode1.model.Medico;

public interface IMedicoRepository extends JpaRepository<Medico, UUID>{

}
