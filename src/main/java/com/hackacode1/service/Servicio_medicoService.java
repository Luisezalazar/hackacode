package com.hackacode1.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Servicio_medico;
import com.hackacode1.repository.IServicio_medicoRepository;
@Service
public class Servicio_medicoService implements IServicio_medicoService{
	
	@Autowired
	IServicio_medicoRepository servicioRepo;
	@Override
	public List<Servicio_medico> getServicio_medico() {
		List<Servicio_medico> listaServicio = servicioRepo.findAll();
		return listaServicio;
	}

	@Override
	public void saveServicio_medico(Servicio_medico servicio) {
	
		servicioRepo.save(servicio);
		
		
	}

	@Override
	public Servicio_medico findServicio_medico(UUID id) {
		Servicio_medico servicio = servicioRepo.findById(id).orElse(null);
		return servicio;
	}

	@Override
	public void deleteServicio_medico(UUID id) {
		servicioRepo.deleteById(id);

	}

	@Override
	public void editServicio_medico(UUID original_id, String newNombre, String newDescripcion, Double newPrecio) {
		Servicio_medico  servicio = this.findServicio_medico(original_id);
		servicio.setNombre(newNombre);
		servicio.setDescripcion(newDescripcion);
		servicio.setPrecio(newPrecio);
		this.saveServicio_medico(servicio);
	}

	@Override
	public void editServicio_medico(Servicio_medico servicio) {
		this.saveServicio_medico(servicio);
	}

	

	
}
