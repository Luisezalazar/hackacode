package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackacode1.model.Personal;

public interface IPersonalRepository extends JpaRepository<Personal, UUID>{
	Personal findByUsername(String username);
}
