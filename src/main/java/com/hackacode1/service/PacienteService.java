package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Paciente;
import com.hackacode1.repository.IPacienteRepository;
@Service
public class PacienteService implements IPacienteService{

	@Autowired
	IPacienteRepository pacienRepo;
	@Override
	public List<Paciente> getPaciente() {
		List<Paciente> listaPacientes = pacienRepo.findAll();
		return listaPacientes;
	}

	@Override
	public void savePaciente(Paciente pacien) {
		pacienRepo.save(pacien);
		
		
	}

	@Override
	public Paciente findPaciente(UUID id) {
		Paciente paciente = pacienRepo.findById(id).orElse(null);
		return paciente;
	}

	@Override
	public void deletePaciente(UUID id) {
		pacienRepo.deleteById(id);
		
	}

	

	@Override
	public void editPaciente(Paciente pacien) {
		this.savePaciente(pacien);
		
	}

	@Override
	public void editPaciente(UUID original_id, Boolean newObra_social, String newNombre, String newApellido,
			String newDni, LocalDate newFecha_nac, String newEmail, String newTelefono, String newDireccion) {

		Paciente paciente = this.findPaciente(original_id);
		paciente.setObra_social(newObra_social);
		paciente.setNombre(newNombre);
		paciente.setApellido(newApellido);
		paciente.setDni(newDni);	
		paciente.setFecha_nac(newFecha_nac);
		paciente.setEmail(newEmail);
		paciente.setTelefono(newTelefono);	
		paciente.setDireccion(newDireccion);	
		this.savePaciente(paciente);

		
	}

}
