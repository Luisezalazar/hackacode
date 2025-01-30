package com.hackacode1.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Paquete_servicio;
import com.hackacode1.repository.IPaquete_servicioRepository;
@Service
public class Paquete_servicioService implements IPaquete_servicioService{

	@Autowired
	IPaquete_servicioRepository paqueteRepo;
	@Override
	public List<Paquete_servicio> getPaquete_servicios() {
		List<Paquete_servicio> listaPaquetes = paqueteRepo.findAll();
		return listaPaquetes;
	}

	@Override
	public void savePaquete_servicio(Paquete_servicio paquete) {
		paqueteRepo.save(paquete);
		
	}

	@Override
	public Paquete_servicio findPaquete_servicio(UUID id) {
		Paquete_servicio paquete = paqueteRepo.findById(id).orElse(null);
		return paquete;
	}

	@Override
	public void deletePaquete_servicio(UUID id) {
		paqueteRepo.deleteById(id);
		
	}

	@Override
	public void editPaquete_servicio(UUID original_id, Double newPrecio) {
		Paquete_servicio paquete = this.findPaquete_servicio(original_id);
		paquete.setPrecio_paquete(newPrecio);
		this.savePaquete_servicio(paquete);
		
	}

	@Override
	public void editPaquete_servicio(Paquete_servicio paquete) {
		this.savePaquete_servicio(paquete);
		
		
	}

}
