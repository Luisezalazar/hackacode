package com.hackacode1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.hackacode1.model.Paquete_servicio;
import com.hackacode1.service.IPaquete_servicioService;

@RestController
@RequestMapping("/paquete_servicio")
public class Paquete_servicioController {
	@Autowired
	IPaquete_servicioService paqueteServ;
	
	@GetMapping("/traer")
	public List<Paquete_servicio> traerPaquetes(){
		List<Paquete_servicio> listaPaquetes = paqueteServ.getPaquete_servicios();
		return listaPaquetes;
	}
	
	@GetMapping("/traer/dni/{dniSolicitante}")
	public List<Paquete_servicio> traerPaqueteDni(@PathVariable String dniSolicitante){
		return paqueteServ.findPaqueteDni(dniSolicitante);
	}
	
	@GetMapping("/traer/{id}")
	public Paquete_servicio traerPaquete(@PathVariable UUID id) {
		return paqueteServ.findPaquete_servicio(id);
	}
	
	@PostMapping("/crear")
	public String crearPaquete(@RequestBody Paquete_servicio paquete) {
		paqueteServ.savePaquete_servicio(paquete);
		
		return "Paquete de servicio creado con exito";
		
	}
	
	@DeleteMapping("/borrar/{id}")
	public String borrarPaquete(@PathVariable UUID id) {
		paqueteServ.deletePaquete_servicio(id);
		return "Paquete de servicio borrado exito";
	}
	
	
}

