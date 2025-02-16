package com.hackacode1.security;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hackacode1.model.Personal;
import com.hackacode1.repository.IPersonalRepository;
import com.hackacode1.service.IPersonalService;

@Configuration
public class InitialDataConfiguration {

	@Autowired
	IPersonalService perServ;
	
	@Autowired
	IPersonalRepository perRepo;
	
	@Bean
	CommandLineRunner run () {
		return args->{System.out.println("Existo padre");
		if(perRepo.count()==0) {
			Personal perso = new Personal();
			perso.setNombre("Admin");
			perso.setApellido("Admin");
			perso.setDireccion("Admin");
			perso.setDni("1001100011");
			perso.setEmail("Admin@Admin.com");
			perso.setFechaNac(LocalDate.parse("2024-10-10"));
			perso.setTelefono("1122223333");
			perso.setUsername("admin");
			perso.setPassword("admin");
			perso.setRol("admin");
			perServ.savePersonal(perso);
		}
		};
}
}
