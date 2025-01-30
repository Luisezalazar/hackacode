package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Personal;

public interface IPersonalService {

	
	public List<Personal> getPersonals();
	
	public void savePersonal(Personal personal);
	
	public void deletePersonal(UUID id);
	
	public Personal getPersonal(UUID id);
	
	public void editPersonal(Personal personal);
	
	public void editPersona(UUID id, String newUsuario, String newContrasenia, String newRol, String newNombre, String newApellido, String newDni, LocalDate newFecha_nac, String newEmail, String newTelefono, String newDireccion);
	
	public Personal findByUsername(String username);
}
