package com.hackacode1.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackacode1.dto.MedicoServicioDTO;
import com.hackacode1.dto.MedicoTurnoDTO;
import com.hackacode1.model.Medico;

import com.hackacode1.service.IMedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	IMedicoService medServ;
	
	@PostMapping("/guardar")
	public Medico guardarguardar (@RequestBody MedicoTurnoDTO medico) {
		Medico newMedico = medServ.guardarMedico(medico);
		return newMedico;
		
	}
	
	@GetMapping("/traer")
	public List<Medico> traerMedicos(){
		List<Medico> listaMedicos = medServ.getMedico();
		return listaMedicos;
	} 
	
	@GetMapping("/traer/{id}")
	public Medico traerMedico(@PathVariable UUID id) {
		return medServ.findMedico(id);
	}
	

	@DeleteMapping("/borrar/{id}")
	public String borrarMedico(@PathVariable UUID id) {
		medServ.deleteMedico(id);
		return "Medico borrado con exito";
	}
	
	@PostMapping("/crear")
	public String crearMedico(@RequestBody Medico medico) {
		
		medServ.saveMedico(medico);
		return "Medico creado con exito";
	}
	
	@PutMapping("/editar/{id}")
	public Medico editarMedico(@PathVariable UUID original_id,
							@RequestParam(required=false, name="nombre") String newNombre,
							@RequestParam(required=false, name="apellido") String newApellido,
							@RequestParam(required=false, name="dni") String newDni,
							@RequestParam(required=false, name="telefono") String newTelefono,
							@RequestParam(required=false, name="email") String newEmail,
							@RequestParam(required=false, name="direccion") String newDireccion,
							@RequestParam(required=false, name="fecha_nacimiento") LocalDate newFecha_nacimiento,
							@RequestParam(required=false, name="especialidad_medica") String newEspecialidad_medica,
							@RequestParam(required=false, name="sueldo") Double newSueldo) {
		medServ.editMedico(original_id,newEspecialidad_medica, newSueldo, newNombre, newApellido, newDni, newFecha_nacimiento, newEmail, newTelefono, newDireccion);
		Medico medico = medServ.findMedico(original_id);
		return medico;
	}

	
	@GetMapping("/servicio_con_medicos/{id}")
	public MedicoServicioDTO getServicioConMedico(@PathVariable UUID id) {
		return medServ.getServicioMedicoConMedicos(id);
	}
}


