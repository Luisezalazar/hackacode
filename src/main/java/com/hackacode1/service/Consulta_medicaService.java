package com.hackacode1.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.CalendarioDTO;
import com.hackacode1.model.Consulta_medica;
import com.hackacode1.repository.IConsulta_medicaRepository;

@Service
public class Consulta_medicaService implements IConsulta_medicaService{

	@Autowired
	IConsulta_medicaRepository consulRepo;
	@Override
	public List<Consulta_medica> getConsultas() {
		List<Consulta_medica> listaConsultas = consulRepo.findAll();
		return listaConsultas;
	}

	@Override
	public void saveConsulta(Consulta_medica consul) {
		consulRepo.save(consul);
		
	}

	@Override
	public Consulta_medica findConsulta(UUID id) {
		Consulta_medica consulta = consulRepo.findById(id).orElse(null);
		return consulta;
	}

	@Override
	public void deleteConsulta(UUID id) {
		consulRepo.deleteById(id);
		
	}

	@Override
	public void editConsulta(UUID original_id, String newfecha_consulta, LocalDate newHora_consulta,
			Double newMonto_total, String newPagado_o_no) {
		Consulta_medica consul = this.findConsulta(original_id);
		consul.setFecha_consulta(newHora_consulta);
		consul.setFecha_consulta(newHora_consulta);
		consul.setMonto_total(newMonto_total);
		consul.setPagado_o_no(newPagado_o_no);
		this.saveConsulta(consul);
				
	}

	@Override
	public void editConsulta(Consulta_medica consul) {
		this.saveConsulta(consul);
	}

	@Override
	public List<CalendarioDTO> getCalendario() {
		List<Consulta_medica> listaConsultas = consulRepo.findAll();
		List<CalendarioDTO> listaResultado = new ArrayList<>();
		for(Consulta_medica consul : listaConsultas) {
			CalendarioDTO dto = new CalendarioDTO();
			dto.setFecha_consulta(consul.getFecha_consulta());
			dto.setHora_consulta(consul.getHora_consulta());
			dto.setMedico(consul.getMedico().getNombre());
			dto.setPaciente(consul.getPaciente().getNombre());
			listaResultado.add(dto);
		}
		return listaResultado;
		
	}

}
