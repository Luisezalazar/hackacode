package com.hackacode1.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackacode1.model.Servicio_medico;
import com.hackacode1.model.Turno;
import com.hackacode1.repository.IServicio_medicoRepository;
import com.hackacode1.repository.ITurnoRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class TurnoService implements ITurnoService{

	@Autowired
	private ITurnoRepository turnoRepo;
	
	@Autowired
	private IServicio_medicoRepository servRepo;
	
	@Override
	public List<Turno> getTurnos() {
		List<Turno> listaTurno =  turnoRepo.findAll();
		return listaTurno;
	}

	@Override
	public Turno getTurno(UUID id_turno) {
		return turnoRepo.findById(id_turno).orElse(null);
	}

	@Override
	public void saveTurno(Turno turno) {
		 //Generar los bloques de turnos
		 List<String> bloques = this.setBloqueTurnos(turno);
		 
		 //Asignar los bloques generados al turno
		 turno.setHoraBloque(bloques);
		 turnoRepo.save(turno); // Guarda el turno con el horario
		
	}

	@Override
	public void deleteTurno(UUID id_turno) {
		turnoRepo.deleteById(id_turno);
		
	}

	@Override
	public void editTurno(UUID id_turno, LocalTime newHoraInicio, LocalTime newHoraFinal, Boolean newDescanso, DayOfWeek newDiaSemana) {
		Turno turno = this.getTurno(id_turno);
		turno.setHoraInicio(newHoraInicio);
		turno.setHoraFinal(newHoraFinal);
		turno.setDescanso(newDescanso);
		turno.setDiaSemana(newDiaSemana);
		this.saveTurno(turno);
		
	}

	@Override
	public void ocuparHora(UUID id_turno, UUID id_servicio_medico, String horaTurno) {
		Optional<Turno> turnoOpt = turnoRepo.findById(id_turno);
		if(!turnoOpt.isPresent()) {
			throw new EntityNotFoundException("No se encontró la jornada del médico");
		}
		Turno turno = turnoOpt.get();
		
		Optional<Servicio_medico> servicioOpt = servRepo.findById(id_servicio_medico);
		if(!servicioOpt.isPresent()) {
			throw new EntityNotFoundException("No se encontró el servicio medico");
		}
		Servicio_medico servicio = servicioOpt.get();
		
		LocalTime duracion = servicio.getDuracion(); //Llegará 00:30
		//Convertimos la duracion de LocalTime a minutos
		int duracionMinutos = duracion.getHour()*60 + duracion.getMinute();
		duracionMinutos -=1;
		LocalTime inicio = LocalTime.parse(horaTurno);
		LocalTime fin = inicio.plusMinutes(duracionMinutos);
		
		turno.setHoraBloque(turno.getHoraBloque().stream()
				.map(LocalTime::parse)
				.filter(hora -> hora.isBefore(inicio) || hora.isAfter(fin))
				.map(LocalTime::toString)
				.collect(Collectors.toList()));
		
		turno.setUltimaHoraOcupada(horaTurno);
		turnoRepo.save(turno);
				
		
		
	}

	@Override
	public List<String> setBloqueTurnos(Turno turno) {
		List<String> horario = new ArrayList<>();
		 LocalTime horaInicio =turno.getHoraInicio(); // Usa los datos del turno guardado
		 LocalTime horaFinal = turno.getHoraFinal();
		 LocalTime intervalo = LocalTime.of(0, 30);
		 LocalTime horaInicioDescanso = turno.getHoraInicioDescanso();
		 LocalTime horaFinalDescanso = turno.getHoraFinalDescanso().minusMinutes(1);
		
		 DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

		 while (horaInicio.isBefore(horaFinal) || horaInicio.equals(horaFinal)) {
			 if(horaInicio.isBefore(horaInicioDescanso) || horaInicio.isAfter(horaFinalDescanso)) {
				 horario.add(horaInicio.format(formatoHora));
			 }
		      
		      horaInicio = horaInicio.plusMinutes(intervalo.getMinute());
		    }
		 return horario;
	}

	@Override
	public void editTurnoo(Turno turno) {
		turnoRepo.save(turno);
		
	}
	
}
