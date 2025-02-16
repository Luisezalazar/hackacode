package com.hackacode1.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Turno;


public interface ITurnoService {
	public List<Turno> getTurnos();
	
	public Turno getTurno(UUID id_turno);
	
	public void saveTurno(Turno turno);
	
	public void deleteTurno(UUID id_turno);
	
	public void editTurno(UUID id_turno, LocalTime newHoraInicio, LocalTime newHoraFinal, Boolean newDescanso, DayOfWeek newDiaSemana);
	
	public void ocuparHora(UUID id_turno, UUID id_servicio_medico, String horaTurno);
	
	public void editTurnoo(Turno turno);

	public List<String> setBloqueTurnos(Turno turno);
}
