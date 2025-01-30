package com.hackacode1.controller;

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

import com.hackacode1.model.Servicio_medico;
import com.hackacode1.service.IServicio_medicoService;

@RestController
@RequestMapping("/servicio_medico")
public class Servicio_medicoController {

	@Autowired
	IServicio_medicoService servServ;
	
	@GetMapping("/traer")
	public List<Servicio_medico> traerServicios(){
		List<Servicio_medico> serv = servServ.getServicio_medico();
		return serv;
	}
	
	@DeleteMapping("/borrar/{id}")
	public String borrarServicio_medico(@PathVariable UUID id) {
		servServ.deleteServicio_medico(id);
		return "El servicio medico ha sido borrado con exito";
	}
	
	@PostMapping("/crear")
	public String crearServicio_medico(@RequestBody Servicio_medico serv) {
		servServ.saveServicio_medico(serv);
		return "El servicio medico ha sido creado con exito";
	}
	
	@GetMapping("/traer/{id}")
	public Servicio_medico traerServicio(@PathVariable UUID id) {
		return servServ.findServicio_medico(id);
	}
	
	@PutMapping("/editar")
	public Servicio_medico editarServicio_medico(@RequestBody Servicio_medico serv) {
		servServ.editServicio_medico(serv);
		return servServ.findServicio_medico(serv.getCodigo_servicio());
	}

	@PutMapping("/editar/{id}")
	public Servicio_medico editarServicio_medico(@PathVariable UUID id,
												@RequestParam(required=false, name="nombre_servicio") String newNombre_servicio,
												@RequestParam(required=false, name="descripcion") String newDescripcion,
												@RequestParam(required=false, name="precio_servicio") Double newPrecio_servicio) {
		servServ.editServicio_medico(id, newNombre_servicio, newDescripcion, newPrecio_servicio);
		Servicio_medico serv = servServ.findServicio_medico(id);
		return serv;
	}
}
