package com.hackacode1.service;

import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Servicio_medico;

public interface IServicio_medicoService {
	
	public List<Servicio_medico> getServicio_medico();
	
	public void saveServicio_medico(Servicio_medico servicio);
	
	public Servicio_medico findServicio_medico(UUID id);
	
	public void deleteServicio_medico(UUID id);
	
	public void editServicio_medico(UUID original_id,String newNombre, String newDescripcion, Double newPrecio);
	
	public void editServicio_medico(Servicio_medico servicio);
	
	
}
