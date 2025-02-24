package com.hackacode1.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.hackacode1.dto.CalendarioDTO;
import com.hackacode1.dto.ConsultasDTO;
import com.hackacode1.dto.GraficoGeneroDTO;
import com.hackacode1.dto.HistorialDTO;
import com.hackacode1.dto.ServicioConteoDTO;
import com.hackacode1.model.Consulta_medica;



public interface IConsulta_medicaService {

	
	public List<ConsultasDTO> getConsultas();
	
	public void saveConsulta(Consulta_medica consul, LocalTime horaTurno, LocalDate fechaTurno);
	
	public Consulta_medica findConsulta(UUID id);
	
	public void deleteConsulta(UUID id);
	
	public void editConsulta(UUID original_id,String newfecha_consulta, LocalDate newHora_consulta, Double newMonto_total, String newPagado_o_no);
	
	public void editConsulta(Consulta_medica consul);
	
	public List<CalendarioDTO> getCalendario();
	
	public List<HistorialDTO> getConsultasPorPacientes(String dni);
	
	public List<Consulta_medica> getConsultasPorMedicos(UUID id);
	
	public GraficoGeneroDTO getGraficoGenero();
	
	public List<ServicioConteoDTO> getCountServicec();
	
}
