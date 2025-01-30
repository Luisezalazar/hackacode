package com.hackacode1.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackacode1.model.Personal;
import com.hackacode1.security.JwtService;
import com.hackacode1.security.MyUserDetailsService;
import com.hackacode1.service.IPersonalService;

@RestController
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	IPersonalService persoServ;
	
	@Autowired
	private JwtService jwtServ;
	
	@Autowired
	private MyUserDetailsService userDetailsServ;
	
	
	@GetMapping("/traer")
	public List<Personal> getPersonals(){
		return persoServ.getPersonals();
	}
	
	@GetMapping("/traer/{id}")
	public Personal getPersonal(@PathVariable UUID id) {
		Personal perso = persoServ.getPersonal(id);
		return perso;
	}
	
	@DeleteMapping("/borrar/{id}")
	public String deletePersonal(@PathVariable UUID id) {
		persoServ.deletePersonal(id);
		return "Personal borrado con exito";
	}
	
	@PostMapping("/crear")
	public String crearPersonal(@RequestBody Personal personal) {
		persoServ.savePersonal(personal);
		return "Personal creado con exito";
	}
	
	@PutMapping("edit")
	public Personal editPersonal(@RequestBody Personal personal) {
		persoServ.editPersonal(personal);
		return persoServ.getPersonal(personal.getId_persona());
		
	}
	
	@PutMapping("/editar/{id}")
	public Personal editarPaciente(@PathVariable UUID original_id,
								@RequestParam (required=false, name="nombre") String newNombre,
								@RequestParam (required=false, name="apellido") String newApellido,
								@RequestParam (required=false, name="dni") String newDni,
								@RequestParam (required=false, name="telefono") String newTelefono,
								@RequestParam (required=false, name="email") String newEmail,
								@RequestParam (required=false, name="direccion") String newDireccion,
								@RequestParam (required=false, name="fecha_nacimiento") LocalDate newFecha_nacimiento,
								@RequestParam (required=false, name="usuario") String newUsuario,
								@RequestParam (required=false, name="contrasenia") String newContrasenia,
								@RequestParam (required=false) String newrol) {
		
		persoServ.editPersona(original_id, newUsuario, newContrasenia, newrol, newNombre, newApellido, newDni, newFecha_nacimiento, newEmail, newTelefono, newDireccion);
		Personal personal = this.getPersonal(original_id);
		return personal;
}
	
	@PostMapping("/login")
	public String login(@RequestBody Personal personal) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(personal.getUsername(), personal.getPassword()));
		
		if(auth.isAuthenticated()) {
			return jwtServ.generateToken(userDetailsServ.loadUserByUsername(personal.getUsername()));
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}
}
