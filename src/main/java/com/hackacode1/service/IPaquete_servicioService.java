package com.hackacode1.service;

import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Paquete_servicio;

public interface IPaquete_servicioService {
	public List<Paquete_servicio> getPaquete_servicios();
	
	public void savePaquete_servicio(Paquete_servicio paquete);
	
	public Paquete_servicio findPaquete_servicio(UUID id);
	
	public List<Paquete_servicio> findPaqueteDni(String dni);
	
	public void deletePaquete_servicio(UUID id);
	
	public Paquete_servicio replaceServicio(UUID paqueteID, UUID deleteServicio, UUID newServicio);
	
	public Paquete_servicio addPaquete_servicio(UUID newPaquete, UUID newServicio);
	
	public void editPaquete_servicio(Paquete_servicio paquete);
}

