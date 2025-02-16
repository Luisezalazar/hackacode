package com.hackacode1.controller;

import java.time.DayOfWeek;
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

import com.hackacode1.model.Turno;

import com.hackacode1.service.ITurnoService;

@RestController
@RequestMapping("/turno")
public class TurnoController {
	
	@Autowired
	private ITurnoService turnServ;
	
	@GetMapping("/traer")
	public List<Turno> getTurnos(){
		return turnServ.getTurnos();
	}
	
	@GetMapping("/traer/{id}")
	public Turno getTurno(@PathVariable UUID id) {
		return turnServ.getTurno(id);
	}
	
	@DeleteMapping("/borrar/{id}")
	public String deleteTurno(@PathVariable UUID id) {
		turnServ.deleteTurno(id);
		return "El turno ha sido borrado con exito";
	}
	
	@PostMapping("/crearJornada")
	public String crearJornada(@RequestBody Turno turno) {
		turnServ.saveTurno(turno);
		return "El turno ha sido creado con exito";
	}
	
	@PutMapping("editar/{id}")
	public Turno editarTurno(@PathVariable UUID id_turno,
							@RequestParam(required=false, name="horaInicio")LocalTime newHoraInicio,
							@RequestParam(required=false, name="horaFinal") LocalTime newHoraFinal,
							@RequestParam(required=false, name="descanso")	Boolean newDescanso,
							@RequestParam(required=false, name="diaSemana")	DayOfWeek newDiaSemana){
		turnServ.editTurno(id_turno, newHoraInicio, newHoraFinal, newDescanso, newDiaSemana);
		Turno turno = turnServ.getTurno(id_turno);
		return turno;
	}
	
	@DeleteMapping("ocuparHora/{id_turno}/{id_servicio}/{horaTurno}")
	public String ocuparHora(@PathVariable UUID id_turno, @PathVariable UUID id_servicio, @PathVariable String horaTurno) {
		turnServ.ocuparHora(id_turno, id_servicio, horaTurno);
		return "El turno ha sido asignado con exito";
	}
}
