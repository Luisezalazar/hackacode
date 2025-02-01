package com.hackacode1.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Paquete_servicio;
import com.hackacode1.model.Servicio_medico;
import com.hackacode1.repository.IPaquete_servicioRepository;
import com.hackacode1.repository.IServicio_medicoRepository;
@Service
public class Paquete_servicioService implements IPaquete_servicioService{

	@Autowired
	IPaquete_servicioRepository paqueteRepo;
	
	@Autowired
	IServicio_medicoRepository servRepo;
	
	
	@Override
	
	public List<Paquete_servicio> getPaquete_servicios() {
		List<Paquete_servicio> listaPaquetes = paqueteRepo.findAll();
		return listaPaquetes;
	}

	@Override
	public void savePaquete_servicio(Paquete_servicio paquete) {
		//Se buscan los ids ya existentes 
		List<Servicio_medico> servicios = servRepo.findAllById(paquete.getServicios_medicos().stream()
				.map(Servicio_medico::getCodigo_servicio)//Se obtiene los codigos de los servicios medicos
				.collect(Collectors.toList())); //Se colectan los codigos en una lista
		
		
		
		if(servicios.isEmpty()) {
			throw new RuntimeException("No se encontraron servicios existentes");
		}
		paquete.setServicios_medicos(servicios);
		
		
		Double suma = servicios.stream()
				.mapToDouble(Servicio_medico::getPrecio)
				.sum();
		
		Double total= suma*0.85;
		paquete.setPrecio_paquete(total);
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

	

}
