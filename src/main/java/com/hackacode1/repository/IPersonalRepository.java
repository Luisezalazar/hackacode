package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackacode1.model.Personal;
@Repository
public interface IPersonalRepository extends JpaRepository<Personal, UUID>{
	Personal findByUsername(String username);
}
