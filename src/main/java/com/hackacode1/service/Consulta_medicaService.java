package com.hackacode1.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackacode1.dto.CalendarioDTO;
import com.hackacode1.dto.ConsultaPaqueteDTO;
import com.hackacode1.dto.ConsultasDTO;
import com.hackacode1.dto.HistorialDTO;
import com.hackacode1.model.Consulta_medica;
import com.hackacode1.model.Paquete_servicio;
import com.hackacode1.model.Servicio_medico;
import com.hackacode1.model.Turno;
import com.hackacode1.repository.IConsulta_medicaRepository;
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
	
	
	@Override
	public List<ConsultasDTO> getConsultas() {
		List<Consulta_medica> listaConsultas = consulRepo.findAll();
		List<ConsultasDTO> listaResultado = new ArrayList<>();
		
		for(Consulta_medica consul: listaConsultas) {
			
			ConsultasDTO dto = new ConsultasDTO();
			dto.setFechaConsulta(consul.getFechaConsulta());
			dto.setHoraTurno(consul.getHoraTurno());
			dto.setMontoTotal(consul.getMontoTotal());
			dto.setPagadoONo(consul.getPagadoONo());
			
			if(consul.getPaciente()!=null) {
			dto.setNombrePaciente(consul.getPaciente().getNombre());
			dto.setApellidoPaciente(consul.getPaciente().getApellido());
			} else throw new EntityNotFoundException("No se encontraron pacientes para agregar");
			
			if(consul.getMedico()!=null) {
			dto.setNombreMedico(consul.getMedico().getNombre());
			dto.setApellidoMedico(consul.getMedico().getApellido());
			} else throw new EntityNotFoundException("No se encontraron mediso para agregar");
			
			
			List<ConsultaPaqueteDTO> listaPaquetes= new ArrayList<>();
			if(consul.getPaquete() != null && consul.getPaquete().getServicios_medicos() !=null) {
				for(Servicio_medico servicio : consul.getPaquete().getServicios_medicos()) {
					ConsultaPaqueteDTO paqdto = new ConsultaPaqueteDTO();
					paqdto.setNombreServicio(servicio.getNombre());
					paqdto.setDescripcion(servicio.getDescripcion());
					listaPaquetes.add(paqdto);
				}
			} else throw new EntityNotFoundException("No se encontró un paquete y/o servicios");
			
			
			dto.setNombrePaquetes(listaPaquetes);
			listaResultado.add(dto);
		}
		return listaResultado;
	}

	
	@Override
	public void saveConsulta(Consulta_medica consul) {
	    // Verificar si la consulta tiene un paquete y obtenerlo si existe
	    Optional<Paquete_servicio> paqueteOpt = Optional.ofNullable(consul.getPaquete())
	        .map(paquete -> paqueteRepo.findById(paquete.getCodigo_paquete()))
	        .orElse(Optional.empty());
	    // Asignar el monto total basado en lo que trae la consulta
	    if (paqueteOpt.isPresent()) {
	        consul.setMontoTotal(paqueteOpt.get().getPrecioPaquete()); // Si tiene paquete, usa su precio
	    } else {
	        consul.setMontoTotal(null); // Si no hay paquete ni servicio
	    }
	    Optional<Turno> turnoOpt = turnoRepo.findById(consul.getTurno().getId_turno());
	    if (!turnoOpt.isPresent()) {
	        throw new EntityNotFoundException("No se encontró el turno asociado");
	    }
	    Turno turno = turnoOpt.get();
	    
	    
	    //consul.setHoraTurno(LocalTime.parse(turno.get().getUltimaHoraOcupada()));
	    //turnoServ.ocuparHora(turno.get().getId_turno(),paqueteOpt.get().getServicios_medicos().get(0), consul.getHoraTurno());
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
		consul.setFechaConsulta(newHora_consulta);
		consul.setFechaConsulta(newHora_consulta);
		consul.setMontoTotal(newMonto_total);
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
			dto.setFecha_consulta(consul.getFechaConsulta());
			
			dto.setMedico(consul.getMedico().getNombre());
			dto.setPaciente(consul.getPaciente().getNombre());
			listaResultado.add(dto);
		}
		return listaResultado;
		
	}

	@Override
	public List<HistorialDTO> getConsultasPorPaciente() {
		List<Consulta_medica> listaConsultas = consulRepo.findAll();
		List<HistorialDTO> histo = new ArrayList<>();
		for(Consulta_medica consul : listaConsultas) {
			HistorialDTO dto = new HistorialDTO();
			dto.setFechaConsulta(consul.getFechaConsulta());
			
			dto.setMontoTotal(consul.getMontoTotal());
			dto.setPagadoONo(consul.getPagadoONo());
			dto.setNombreMedico(consul.getMedico().getNombre());
			//Setear el nombre de los servicios teniendo en cuenta todo lo que tiene el paquete
			List<ConsultaPaqueteDTO> listaPaquetes= new ArrayList<>();
			if(consul.getPaquete() != null && consul.getPaquete().getServicios_medicos() !=null) {
				for(Servicio_medico servicio : consul.getPaquete().getServicios_medicos()) {
					ConsultaPaqueteDTO paqdto = new ConsultaPaqueteDTO();
					paqdto.setNombreServicio(servicio.getNombre());
					paqdto.setDescripcion(servicio.getDescripcion());
					listaPaquetes.add(paqdto);
				}
			} else throw new EntityNotFoundException("No se encontró un paquete y/o servicios");
			
			
			dto.setNombrePaquetes(listaPaquetes);
			//
			dto.setNombrePaciente(consul.getPaciente().getNombre());
			histo.add(dto);
			
		}
		return histo;
		
		}

	@Override
	public List<HistorialDTO> getConsultasPorPacientes(String dni) {
		 // Buscar todas las consultas médicas
		 List<Consulta_medica> consultas = consulRepo.findByPacienteIdPersona(dni);
		 return consultas.stream()
		        .map(consulta -> {
		 // Crear DTO
		 HistorialDTO dto = new HistorialDTO(
		                consulta.getFechaConsulta(),
		                consulta.getMontoTotal(),
		                consulta.getPagadoONo(),
		                consulta.getMedico().getNombre(),
		                consulta.getPaciente().getNombre(),
		                new ArrayList<>()
		  );

		  // Si la consulta tiene un paquete, obtener los servicios médicos
		  if (consulta.getPaquete() != null && consulta.getPaquete().getServicios_medicos() != null) {
			  
		  List<ConsultaPaqueteDTO> servicios = consulta.getPaquete().getServicios_medicos().stream()
				  
		      .map(servicio -> new ConsultaPaqueteDTO(servicio.getNombre(), servicio.getDescripcion()))
		      .collect(Collectors.toList());
		      dto.setNombrePaquetes(servicios);
		      }
		            return dto;
		      })
		        .collect(Collectors.toList());
		}
			
		}
		



