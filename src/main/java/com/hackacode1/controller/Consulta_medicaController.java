package com.hackacode1.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.hackacode1.model.CalendarioDTO;
import com.hackacode1.model.Consulta_medica;
import com.hackacode1.service.IConsulta_medicaService;

@RestController
@RequestMapping("/consulta_medica")
public class Consulta_medicaController {

	@Autowired
	private IConsulta_medicaService consulServ;
	
	@GetMapping("/calendario")
	public List<CalendarioDTO> getConsultaMedica(){
		return consulServ.getCalendario();
	}
	
	@GetMapping("/traer")
	public List<Consulta_medica> getConsultas(){
		List<Consulta_medica> listaConsultas = consulServ.getConsultas();
		return listaConsultas;
	}
	
	@GetMapping("/traer/{id}")
	public Consulta_medica getFindConsultas(@PathVariable UUID id) {
		return consulServ.findConsulta(id);
	}
	
	@PostMapping("/crear")
	public String crearConsulta(@RequestBody Consulta_medica consul) {
		consulServ.saveConsulta(consul);
		return "Consulta creada con exito";
	}
	@PutMapping("/edit/{id}")
	public Consulta_medica editarConsultaMedica(@PathVariable UUID id_original,
												@RequestParam(required=false, name="fecha_consulta") LocalDate newFecha_consulta,
												@RequestParam(required=false, name="hora_consulta") LocalTime newHora_consulta,
												@RequestParam(required=false, name="monto_total") Double newMonto_total,
												@RequestParam(required=false) String pagado_o_no) {
		consulServ.editConsulta(id_original, pagado_o_no, newFecha_consulta, newMonto_total, pagado_o_no);
		Consulta_medica consul = consulServ.findConsulta(id_original);
		return consul;
									
	}
	
	@PutMapping("/edit")
	public Consulta_medica editConsulta(@RequestBody Consulta_medica consul) {
		consulServ.editConsulta(consul);
		return consulServ.findConsulta(consul.getId_consulta_medica());
	}
	
	@DeleteMapping("/delete/{id}")
	public String borrarConsulta(@PathVariable UUID id) {
		consulServ.deleteConsulta(id);
		return "Consulta borrada con exito";
	}
	
	
	
}

