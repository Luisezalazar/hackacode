package com.hackacode1.service;

import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Paquete_servicio;

public interface IPaquete_servicioService {
	public List<Paquete_servicio> getPaquete_servicios();
	
	public void savePaquete_servicio(Paquete_servicio paquete);
	
	public Paquete_servicio findPaquete_servicio(UUID id);
	
	public void deletePaquete_servicio(UUID id);
	
	public void editPaquete_servicio(UUID original_id,Double newPrecio);
	
	public void editPaquete_servicio(Paquete_servicio paquete);
}

