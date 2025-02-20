package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Paciente;

public interface IPacienteService {
	
	public List<Paciente> getPaciente();
	
	public Paciente savePaciente(Paciente pacien);
	
	public Paciente findPaciente(UUID id);
	
	public void deletePaciente(UUID id);
	
	public void editPaciente(UUID original_id,Boolean newObra_social,String newNombre, String newApellido, String newDni, LocalDate newFecha_nac, String newEmail, String newTelefono, String newDireccion);
	
	public void editPaciente(Paciente pacien);
	
	public Paciente findPacienteDni(String dni);
}
