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

import com.hackacode1.model.Paciente;
import com.hackacode1.service.IPacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
	
	@Autowired
	IPacienteService pacienServ;
	
	@GetMapping("/traer")
	public List<Paciente> traerPacientes (){
		List<Paciente> listaPaciente = pacienServ.getPaciente();
		return listaPaciente;
	}
	
	@GetMapping("traer/{id}")
	public Paciente traerPaciente(@PathVariable UUID id) {
		return pacienServ.findPaciente(id);
	}
	
	@GetMapping("buscar/{dni}")
	public Paciente buscarPacienteDni(@PathVariable String dni) {
		return pacienServ.findPacienteDni(dni);
	}
	
	@DeleteMapping("/borrar/{id}")
	public String borrarPaciente(@PathVariable UUID id) {
		pacienServ.deletePaciente(id);
		return "Paciente borrado con exito";
	}
	
	@PostMapping("/crear")
	public String crearPaciente(@RequestBody Paciente paciente) {
		pacienServ.savePaciente(paciente);
		return "Paciente creado con exito";
		
	}
	
	@PutMapping("/editar/{id}")
	public Paciente editarPaciente(@PathVariable UUID original_id,
								@RequestParam (required=false, name="nombre") String newNombre,
								@RequestParam (required=false, name="apellido") String newApellido,
								@RequestParam (required=false, name="dni") String newDni,
								@RequestParam (required=false, name="telefono") String newTelefono,
								@RequestParam (required=false, name="email") String newEmail,
								@RequestParam (required=false, name="direccion") String newDireccion,
								@RequestParam (required=false, name="fecha_nacimiento") LocalDate newFecha_nacimiento,
								@RequestParam (required=false, name="obra_social") Boolean newObra_social) {

		pacienServ.editPaciente(original_id, newObra_social, newNombre, newApellido, newDni, newFecha_nacimiento, newEmail, newTelefono, newDireccion);
		Paciente paciente = pacienServ.findPaciente(original_id);
		return paciente;

}
	
	@PutMapping("/editar")
	public Paciente editarPaciente(@RequestBody Paciente paciente) {
		pacienServ.editPaciente(paciente);
		return pacienServ.findPaciente(paciente.getId_persona());
	}
}

