package com.hackacode1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackacode1.model.Consulta_medica;
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
		
		// 1. Obtener los IDs de los servicios asociados a las consultas médicas
		List<UUID> listaUUID = paquete.getConsultas().stream()
		     .map(consulta -> consulta.getServicio().getCodigo_servicio()) // Obtener el ID del servicio directamente
		     .collect(Collectors.toList());

		//2. Verificar si todos los servicios existen en la base de datos
	    List<Servicio_medico> servicios = servRepo.findAllById(listaUUID);

	    if (servicios.size() != servicios.size()) {
	        throw new EntityNotFoundException("No se encontraron todos los servicios especificados.");
	    }
	    
	   //3. Obtener el paciente asociado al paquete
	    Paciente paciente = pacienRepo.findById(paquete.getConsultas().get(0).getPaciente().getId_persona())
	            .orElseThrow(() -> new EntityNotFoundException("No se encontró al paciente asociado al paquete"));
	    		
	    
	    List<Consulta_medica> nuevasConsultas = servicios.stream()
	            .map(servicio -> {
	            	//Se copian los atributos para tenerlos
	                Consulta_medica nuevaConsulta = new Consulta_medica();
	                nuevaConsulta.setServicio(servicio);
	                return nuevaConsulta;
	            })
	            .collect(Collectors.toList());

	    paquete.setConsultas(nuevasConsultas); // Reemplazar la lista de consultas con la nueva lista
		
	    
	   //5. Validar que siempre haya al menos un servicio
	    if (paquete.getConsultas() == null || paquete.getConsultas().isEmpty()) {
	        throw new EntityNotFoundException("No se encontraron servicios médicos.");
	    }
	    
		//Validar que siempre haya un servicio
		if(paquete.getConsultas() == null) {
			throw new EntityNotFoundException("No se encontraron servicios medicos");
		}
		
		//Validar que el paquete tenga al menos 2 servicios
	    if (paquete.getConsultas().size() >= 2) {
	    	Double suma = servicios.stream()
					.mapToDouble(Servicio_medico::getPrecio)
					.sum();
			
			Double total= suma*0.85; //Descuento base del 15%
			
			if(paciente.getObraSocial()==true) {
				total *=0.80;//Descuento del 20%
			}
			paquete.setPrecioPaquete(total);
	    }
	    if(paquete.getConsultas().size() == 1) {
			Double unidad = paquete.getConsultas().get(0).getServicio().getPrecio();
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
	public void editPaquete_servicio(Paquete_servicio paquete) {
		paqueteRepo.save(paquete);
		
	}
	
	@Override
	public Paquete_servicio addPaquete_servicio(UUID newPaquete, UUID newServicio) {
		 // 1. Buscar el paquete de servicio
	    Paquete_servicio paquete = paqueteRepo.findById(newPaquete)
	            .orElseThrow(() -> new EntityNotFoundException("El paquete de servicio no existe"));

	    // 2. Verificar que el servicio existe
	    Servicio_medico servicio = servServ.findServicio_medico(newServicio);
	    if(servicio==null){
	        throw new EntityNotFoundException("El servicio médico no existe");
	    }

	    // 3. Obtener los servicios médicos existentes en las consultas del paquete
	    List<Servicio_medico> serviciosExistentes = new ArrayList<>();
	    for (Consulta_medica consulta : paquete.getConsultas()) {
	        serviciosExistentes.add(consulta.getServicio());
	    }

	    // 4. Agregar el nuevo servicio médico a la lista de servicios existentes
	    serviciosExistentes.add(servicio);

	    List<Consulta_medica> nuevasConsultas = new ArrayList<>();
	    for (Servicio_medico servicioExistente : serviciosExistentes) {
	        Consulta_medica nuevaConsulta = new Consulta_medica();
	        nuevaConsulta.setServicio(servicioExistente);
	        // Si no copias los atributos, se perderán.
	        nuevasConsultas.add(nuevaConsulta);
	    }

	    paquete.setConsultas(nuevasConsultas); // Reemplazar la lista de consultas con la nueva lista

	    // 6. Calcular el nuevo precio con el descuento
	    double total = serviciosExistentes.stream()
	            .mapToDouble(Servicio_medico::getPrecio)
	            .sum();
	    paquete.setPrecioPaquete(total * 0.85);

	    // 7. Guardar el paquete actualizado
	    return paqueteRepo.save(paquete);
		
		
	}

	@Override
	public Paquete_servicio replaceServicio(UUID paqueteId, UUID deleteServicio, UUID newServicio) {
		// 1. Buscar el paquete de servicio
	    Paquete_servicio paquete = paqueteRepo.findById(paqueteId)
	            .orElseThrow(() -> new EntityNotFoundException("El paquete de servicio no existe"));

	    // 2. Obtener los servicios médicos existentes en las consultas del paquete
	    List<Servicio_medico> servicios = paquete.getConsultas().stream()
	            .map(Consulta_medica::getServicio)
	            .collect(Collectors.toList());

	    // 3. Eliminar el servicio a reemplazar
	    boolean eliminado = servicios.removeIf(servicio -> servicio.getCodigo_servicio().equals(deleteServicio));

	    if (!eliminado) {
	        throw new EntityNotFoundException("El servicio a eliminar no existe en este paquete");
	    }

	    // 4. Buscar el nuevo servicio médico
	    Servicio_medico nuevoServicio = servServ.findServicio_medico(newServicio);

	    if (nuevoServicio == null) {
	        throw new EntityNotFoundException("El servicio médico a agregar no existe");
	    }

	    // 5. Agregar el nuevo servicio a la lista
	    servicios.add(nuevoServicio);

	    // 6. Actualizar las consultas del paquete con los nuevos servicios
	    List<Consulta_medica> nuevasConsultas = servicios.stream()
	            .map(servicio -> {
	                Consulta_medica nuevaConsulta = new Consulta_medica();
	                nuevaConsulta.setServicio(servicio);
	                // Copiar otros atributos de las consultas originales si es necesario
	                return nuevaConsulta;
	            })
	            .collect(Collectors.toList());

	    paquete.setConsultas(nuevasConsultas);

	    // 7. Calcular el nuevo precio con descuento
	    double total = servicios.stream()
	            .mapToDouble(Servicio_medico::getPrecio)
	            .sum();
	    paquete.setPrecioPaquete(total * 0.85);

	    // 8. Guardar el paquete actualizado
	    return paqueteRepo.save(paquete);


}
}