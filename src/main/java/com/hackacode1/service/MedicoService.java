package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.dto.MedicoDTO;
import com.hackacode1.dto.MedicoServicioDTO;
import com.hackacode1.model.Medico;
import com.hackacode1.model.Servicio_medico;
import com.hackacode1.model.Turno;
import com.hackacode1.repository.IMedicoRepository;
import com.hackacode1.repository.IServicio_medicoRepository;
@Service
public class MedicoService implements IMedicoService{

	@Autowired
	private IMedicoRepository mediRepo;
	@Autowired
	private IServicio_medicoRepository servicioRepo;
	@Autowired
	private ITurnoService turnServ;
	
	@Override
	public List<Medico> getMedico() {
		List<Medico> listaMedicos = mediRepo.findAll();
		return listaMedicos;
	}

	@Override
	public void saveMedico(Medico medico) {
		for(Turno t : medico.getListaTurno()) {
			t.setHoraBloque(turnServ.setBloqueTurnos(t));
			  
			
		}
		mediRepo.save(medico);
	}

	@Override
	public Medico findMedico(UUID id) {
		Medico medico = mediRepo.findById(id).orElse(null);
		return medico;
	}

	@Override
	public void deleteMedico(UUID id) {
		mediRepo.deleteById(id);
		
	}

	@Override
	public void editMedico(UUID original_id, String newEspecialidad_medica, Double newSueldo, String newNombre, 
	String newApellido, String newDni, LocalDate newFecha_nac, 
	String newEmail, String newTelefono, String newDireccion) {
		Medico medico = this.findMedico(original_id);
		medico.setNombre(newNombre);
		medico.setApellido(newApellido);	
		medico.setDni(newDni);
		medico.setFechaNac(newFecha_nac);
		medico.setEmail(newEmail);
		medico.setTelefono(newTelefono);
		medico.setDireccion(newDireccion);
		medico.setSueldo(newSueldo);
		mediRepo.save(medico);
		
	}

	@Override
	public MedicoServicioDTO getServicioMedicoConMedicos(UUID codigo_servicio) {
		//Buscar el servicio medico
		Optional<Servicio_medico> servicioOpt = servicioRepo.findById(codigo_servicio);
		if(servicioOpt.isPresent()) {
			Servicio_medico servicio = servicioOpt.get();
			
		List<Medico> medicos = mediRepo.findAll();
		
		List<MedicoDTO> medicosServicios = medicos.stream()
				.filter(medico -> medico.getServiciosMedicos().contains(servicio))
				.map(medico -> new MedicoDTO(medico.getId_persona(), medico.getNombre(), medico.getApellido(),medico.getListaTurno()))
				.collect(Collectors.toList());
		return new MedicoServicioDTO(servicio.getNombre(), servicio.getPrecio(),medicosServicios);
		}
		
		return null;
	}

	@Override
	public void editMedicoo(Medico medico) {
		mediRepo.save(medico);
	}
}
