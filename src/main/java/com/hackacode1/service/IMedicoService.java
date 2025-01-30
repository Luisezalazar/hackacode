package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Medico;

public interface IMedicoService {

public List<Medico> getMedico();
	
	public void saveMedico(Medico medico);
	
	public Medico findMedico(UUID id);
	
	public void deleteMedico(UUID id);
	
	public void editMedico(UUID original_id,String newEspecialidad_medica, Double newSueldo, String newNombre, String newApellido, String newDni, LocalDate newFecha_nac, String newEmail, String newTelefono, String newDireccion);
	
	public void editMedico(Medico Medico);
}