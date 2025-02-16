package com.hackacode1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Paciente;
import com.hackacode1.model.Paquete_servicio;
import com.hackacode1.model.Servicio_medico;
import com.hackacode1.repository.IPacienteRepository;
import com.hackacode1.repository.IPaquete_servicioRepository;
import com.hackacode1.repository.IServicio_medicoRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class Paquete_servicioService implements IPaquete_servicioService{

	@Autowired
	IPaquete_servicioRepository paqueteRepo;
	
	@Autowired
	IServicio_medicoRepository servRepo;
	
	@Autowired
	IServicio_medicoService servServ;
	
	@Autowired
	IPacienteRepository pacienRepo;
	
	
	@Override
	
	public List<Paquete_servicio> getPaquete_servicios() {
		List<Paquete_servicio> listaPaquetes = paqueteRepo.findAll();
		return listaPaquetes;
	}

	@Override
	public void savePaquete_servicio(Paquete_servicio paquete) {
		
		
		
		//Se buscan los ids ya existentes 
	    List<UUID> listaUUID = paquete.getServicios_medicos().stream()
	            .map(Servicio_medico::getCodigo_servicio)
	            .collect(Collectors.toList());
		
	    List<Servicio_medico> servicios = servRepo.findAllById(listaUUID);

		
	    if (servicios.size() != servicios.size()) {
	        throw new EntityNotFoundException("No se encontraron todos los servicios especificados.");
	    }
	    
	    //Obtener el paciente para verificar si tiene obraSocial
	    Paciente paciente = pacienRepo.findById(paquete.getPaciente().getId_persona()).orElseThrow(()->
	    new EntityNotFoundException("No se encontró al paciente asociado al paquete"));
	    		
	    
	    //Asignar los servicios al paquete
		paquete.setServicios_medicos(servicios);
		
		//Validar que siempre haya un servicio
		if(paquete.getServicios_medicos() == null) {
			throw new EntityNotFoundException("No se encontraron servicios medicos");
		}
		
		//Validar que el paquete tenga al menos 2 servicios
	    if (paquete.getServicios_medicos().size() >= 2) {
	    	Double suma = servicios.stream()
					.mapToDouble(Servicio_medico::getPrecio)
					.sum();
			
			Double total= suma*0.85; //Descuento base del 15%
			
			if(paciente.getObraSocial()==true) {
				total *=0.80;//Descuento del 20%
			}
			paquete.setPrecioPaquete(total);
	    }
	    if(paquete.getServicios_medicos().size() == 1) {
			Double unidad = paquete.getServicios_medicos().get(0).getPrecio();
			if(paciente.getObraSocial()==true){
				unidad *=0.80;//Descuento del 20%
			}
			paquete.setPrecioPaquete(unidad);
		}
		
		
	    
		
		
		
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
	public void addPaquete_servicio(UUID newPaquete, UUID newServicio) {
		//Buscar el paquete de servicio
		Paquete_servicio paquete = this.findPaquete_servicio(newPaquete);
		//Buscar el servicio medico
		Servicio_medico servicio = servServ.findServicio_medico(newServicio);
		if(servicio==null) {
			throw new RuntimeException("El servicio médico no existe");
		}
		List<Servicio_medico> servicioUpdate = new ArrayList<>(paquete.getServicios_medicos());
		servicioUpdate.add(servicio);
		paquete.setServicios_medicos(servicioUpdate);
		
		//Calcular el nuevo precio con el descuento
		Double total= servicioUpdate.stream()
				.mapToDouble(Servicio_medico::getPrecio)
				.sum();
		paquete.setPrecioPaquete(total*0.85);
		paqueteRepo.save(paquete);
		
		
	}

	@Override
	public void replaceServicio(UUID paqueteID, UUID deleteServicio, UUID newServicio) {
		//Buscar paquete
		Paquete_servicio paquete = this.findPaquete_servicio(paqueteID);
		List<Servicio_medico> servicioActual = new ArrayList<>(paquete.getServicios_medicos());
		boolean eliminado = servicioActual.removeIf(servicio -> servicio.getCodigo_servicio().equals(deleteServicio));
		
		if(!eliminado) {
			throw new RuntimeException("EL servicio a eliminar no existe en este paquete");
		}
		
		//Buscar el nuevo servicio medico
		Servicio_medico newServ = servServ.findServicio_medico(newServicio);
		
		if (newServ == null) {
	        throw new RuntimeException("El servicio médico a agregar no existe");
	    }
		//Agregar el nuevo servicio a la lista existente
		servicioActual.add(newServ);
		//Setear la lista actualizada
		paquete.setServicios_medicos(servicioActual);
		
		//Calcular el nuevo descuento
		Double total = servicioActual.stream()
				.mapToDouble(Servicio_medico::getPrecio)
				.sum();
		paquete.setPrecioPaquete(total*0.85);
		paqueteRepo.save(paquete);

}

	@Override
	public void editPaquete_servicio(Paquete_servicio paquete) {
		paqueteRepo.save(paquete);
		
	}
}