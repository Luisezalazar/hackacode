package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Personal;
import com.hackacode1.repository.IPersonalRepository;

@Service
public class PersonalService implements IPersonalService{
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	IPersonalRepository persoRepo;
	
	@Override
	public List<Personal> getPersonals() {
		return persoRepo.findAll();
	}

	@Override
	public void savePersonal(Personal personal) {
		personal.setPassword(encoder.encode(personal.getPassword()));
		persoRepo.save(personal);
		
	}

	@Override
	public void deletePersonal(UUID id) {
		persoRepo.deleteById(id);
		
	}

	@Override
	public Personal getPersonal(UUID id) {
		Personal perso = persoRepo.findById(id).orElse(null);
		return perso;
	}

	@Override
	public void editPersonal(Personal personal) {
		this.savePersonal(personal);
		
	}

	@Override
	public void editPersona(UUID id, String newUsuario, String newContrasenia, String newRol,String newNombre, String newApellido, String newDni, LocalDate newFecha_nac, String newEmail, String newTelefono, String newDireccion) {
		Personal perso = this.getPersonal(id);
		perso.setNombre(newNombre);
		perso.setApellido(newApellido);
		perso.setDni(newDni);
		perso.setFecha_nac(newFecha_nac);
		perso.setEmail(newEmail);
		perso.setTelefono(newTelefono);
		perso.setDireccion(newDireccion);
		perso.setUsername(newUsuario);
		perso.setPassword(newContrasenia);
		perso.setRol(newRol);
		
	}

	@Override
	public Personal findByUsername(String username) {
		return persoRepo.findByUsername(username);
	}
	
}
