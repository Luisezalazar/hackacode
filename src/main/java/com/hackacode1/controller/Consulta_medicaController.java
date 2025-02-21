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
import com.hackacode1.dto.CalendarioDTO;
import com.hackacode1.dto.ConsultasDTO;
import com.hackacode1.dto.HistorialDTO;
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
	public List<ConsultasDTO> getConsultas(){
		List<ConsultasDTO> listaConsultas = consulServ.getConsultas();
		return listaConsultas;
	}
	
	@GetMapping("/traer/{id}")
	public Consulta_medica getFindConsultas(@PathVariable UUID id) {
		return consulServ.findConsulta(id);
	}
	
	@PostMapping("/crear")
	public String crearConsulta(@RequestBody Consulta_medica consul) {
		
		consulServ.saveConsulta(consul, consul.getHoraTurno(), consul.getFechaTurno());
		
		return "Consulta creada con exito";
	}
	
	
	@PutMapping("/edit/{id}")
	public Consulta_medica editarConsultaMedica(@PathVariable UUID id_original,
												@RequestParam (required=false, name="fechaConsulta") LocalDate newFechaConsulta,
												@RequestParam (required=false, name="horaConsulta") LocalTime newHoraConsulta,
												@RequestParam (required=false, name="montoTotal") Double newMontoTotal,
												@RequestParam (required=false) String pagadoONo) {
		consulServ.editConsulta(id_original, pagadoONo, newFechaConsulta, newMontoTotal, pagadoONo);
		Consulta_medica consul = consulServ.findConsulta(id_original);
		return consul;
									
	}
	
	@PutMapping("/edit")
	public Consulta_medica editConsulta(@RequestBody Consulta_medica consul) {
		consulServ.editConsulta(consul);
		return consulServ.findConsulta(consul.getIdConsultaMedica());
	}
	
	@DeleteMapping("/delete/{id}")
	public String borrarConsulta(@PathVariable UUID id) {
		consulServ.deleteConsulta(id);
		return "Consulta borrada con exito";
	}
	
	@GetMapping("/historial/{dni}")
	public List<HistorialDTO> getConsultasPorPaciente(@PathVariable String dni){
		return consulServ.getConsultasPorPacientes(dni);
	}
	
}

