package com.hackacode1.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackacode1.dto.CalendarioDTO;
import com.hackacode1.dto.ConsultaPaqueteDTO;
import com.hackacode1.dto.ConsultasDTO;
import com.hackacode1.dto.HistorialDTO;
import com.hackacode1.model.Consulta_medica;
import com.hackacode1.repository.IConsulta_medicaRepository;
import com.hackacode1.repository.IMedicoRepository;
import com.hackacode1.repository.IPacienteRepository;
import com.hackacode1.repository.IPaquete_servicioRepository;
import com.hackacode1.repository.IServicio_medicoRepository;
import com.hackacode1.repository.ITurnoRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class Consulta_medicaService implements IConsulta_medicaService{

	@Autowired
	IConsulta_medicaRepository consulRepo;
	
	@Autowired
	IServicio_medicoRepository servRepo;
	
	@Autowired
	IPaquete_servicioRepository paqueteRepo;
	
	@Autowired
	ITurnoRepository turnoRepo;
	@Autowired
	ITurnoService turnoServ;
	@Autowired
	IPacienteRepository pacienteRepo;
	@Autowired
	IMedicoRepository medicoRepo;
	
	@Override
	public List<ConsultasDTO> getConsultas() {
		List<Consulta_medica> listaConsultas = consulRepo.findAll();
		List<ConsultasDTO> listaResultado = new ArrayList<>();
		
		for(Consulta_medica consul: listaConsultas) {
			
			ConsultasDTO dto = new ConsultasDTO();
			dto.setFechaConsulta(consul.getFechaTurno());
			dto.setHoraTurno(consul.getHoraTurno());
			
			dto.setPagadoONo(consul.getPagadoONo());
			
			if(consul.getPaciente()!=null) {
			dto.setNombrePaciente(consul.getPaciente().getNombre());
			dto.setApellidoPaciente(consul.getPaciente().getApellido());
			} else throw new EntityNotFoundException("No se encontraron pacientes para agregar");
			
			if(consul.getMedico()!=null) {
			dto.setNombreMedico(consul.getMedico().getNombre());
			dto.setApellidoMedico(consul.getMedico().getApellido());
			} else throw new EntityNotFoundException("No se encontraron medico para agregar");
			
			
			 List<ConsultaPaqueteDTO> listaPaquetes = new ArrayList<>();
		        if (consul.getServicio() != null) { // Verifica solo el servicio
		            ConsultaPaqueteDTO paqdto = new ConsultaPaqueteDTO();
		            paqdto.setNombre(consul.getServicio().getNombre());
		            paqdto.setDescripcion(consul.getServicio().getDescripcion());
		            listaPaquetes.add(paqdto);
		        }
			 else throw new EntityNotFoundException("No se encontraron servicios");
			
			dto.setNombrePaquetes(listaPaquetes);
			listaResultado.add(dto);
		}
		return listaResultado;
	}

	
	@Override
	public void saveConsulta(Consulta_medica consul, LocalTime horaTurno, LocalDate fechaTurno ) {
	    
	    	consul.setHoraTurno(horaTurno);
	    	consul.setFechaTurno(fechaTurno);
	    
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
		consul.setFechaTurno(newHora_consulta);
		//Arreglar fechaTurno
		consul.setPagadoONo(newPagado_o_no);
		consulRepo.save(consul);
				
	}

	@Override
	public void editConsulta(Consulta_medica consul) {
		consulRepo.save(consul);
	}

	@Override
	public List<CalendarioDTO> getCalendario() {
		List<Consulta_medica> listaConsultas = consulRepo.findAll();
		List<CalendarioDTO> listaResultado = new ArrayList<>();
		for(Consulta_medica consul : listaConsultas) {
			CalendarioDTO dto = new CalendarioDTO();
			//dto.getFecha_consulta(consul.getFechaTurno());
			
			dto.setMedico(consul.getMedico().getNombre());
			dto.setPaciente(consul.getPaciente().getNombre());
			listaResultado.add(dto);
		}
		return listaResultado;
		
	}

	@Override
	public List<HistorialDTO> getConsultasPorPacientes(String dni) {
		// 1. Buscar las consultas médicas por DNI del paciente
	    List<Consulta_medica> consultas = consulRepo.findByPacienteIdPersona(dni);

	    // 2. Mapear las consultas a HistorialDTO
	    return consultas.stream()
	            .map(consulta -> {
	                // 3. Crear HistorialDTO
	                HistorialDTO dto = new HistorialDTO(
	                		
	                		consulta.getMedico().getNombre(),
	                		consulta.getMedico().getApellido(),
	                		consulta.getMedico().getDni(),
	                		consulta.getMedico().getEspecialidadMedica(),
	                		consulta.getFechaTurno(),
	                		consulta.getHoraTurno(),
	                        
	                		consulta.getPaciente().getNombre(),
	                		consulta.getPaciente().getApellido(),
	                		consulta.getPaciente().getGenero(),
	                		consulta.getPaciente().getFechaNac(),
	                		consulta.getPaciente().getDni(),
	                		consulta.getPaciente().getTelefono(),
	                		consulta.getPaciente().getEmail(),
	                		consulta.getPaciente().getDireccion(),
	                		
	                		consulta.getPagadoONo(),
	                		
	                        consulta.getServicio().getNombre(),
	                        consulta.getServicio().getDescripcion(),
	                        consulta.getServicio().getPrecio(),
	                        consulta.getServicio().getDuracion()
	                );

	                

	                return dto;
	            })
	            .collect(Collectors.toList());
		}


	@Override
	public List<Consulta_medica> getConsultasPorMedicos(UUID idMedico) {
		return consulRepo.findByMedicoIdPersona(idMedico);
		
	}
	}
			
		
		



