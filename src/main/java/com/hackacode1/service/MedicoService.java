package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Medico;
import com.hackacode1.repository.IMedicoRepository;
@Service
public class MedicoService implements IMedicoService{

	@Autowired
	IMedicoRepository mediRepo;
	@Override
	public List<Medico> getMedico() {
		List<Medico> listaMedicos = mediRepo.findAll();
		return listaMedicos;
	}

	@Override
	public void saveMedico(Medico medico) {
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
		medico.setFecha_nac(newFecha_nac);
		medico.setEmail(newEmail);
		medico.setTelefono(newTelefono);
		medico.setDireccion(newDireccion);
		medico.setSueldo(newSueldo);
		this.saveMedico(medico);
		
	}

	@Override
	public void editMedico(Medico Medico) {
		 this.saveMedico(Medico);
		
		
	}

}
