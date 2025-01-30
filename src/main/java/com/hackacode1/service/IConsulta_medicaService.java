package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hackacode1.model.CalendarioDTO;
import com.hackacode1.model.Consulta_medica;



public interface IConsulta_medicaService {

	
	public List<Consulta_medica> getConsultas();
	
	public void saveConsulta(Consulta_medica consul);
	
	public Consulta_medica findConsulta(UUID id);
	
	public void deleteConsulta(UUID id);
	
	public void editConsulta(UUID original_id,String newfecha_consulta, LocalDate newHora_consulta, Double newMonto_total, String newPagado_o_no);
	
	public void editConsulta(Consulta_medica consul);
	
	public List<CalendarioDTO> getCalendario();
}
