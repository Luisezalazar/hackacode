package com.hackacode1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackacode1.model.Factura;

public interface IFacturaRepository extends JpaRepository<Factura, UUID>{

}
