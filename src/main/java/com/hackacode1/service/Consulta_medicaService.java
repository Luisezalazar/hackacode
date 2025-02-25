package com.hackacode1.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackacode1.dto.CalendarioDTO;
import com.hackacode1.dto.ConsultaPaqueteDTO;
import com.hackacode1.dto.ConsultasDTO;
import com.hackacode1.dto.GraficoGeneroDTO;
import com.hackacode1.dto.HistorialDTO;
import com.hackacode1.dto.MesConteoDTO;
import com.hackacode1.dto.ServicioConteoDTO;
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
	    	    .map(consulta -> new HistorialDTO(
	    	        consulta.getPagadoONo(),
	    	        consulta.getMedico().getNombre(),
	    	        consulta.getMedico().getApellido(),
	    	        consulta.getMedico().getEspecialidadMedica(),
	    	        consulta.getMedico().getDni(),
	    	        consulta.getFechaTurno(),
	    	        consulta.getHoraTurno(),
	    	        consulta.getPaciente().getNombre(),
	    	        consulta.getPaciente().getApellido(),
	    	        consulta.getPaciente().getGenero(),
	    	        consulta.getPaciente().getDni(),
	    	        consulta.getPaciente().getFechaNac(),
	    	        
	    	        consulta.getPaciente().getTelefono(),
	    	        consulta.getPaciente().getEmail(),
	    	        consulta.getPaciente().getDireccion(),
	    	        consulta.getServicio().getNombre(),
	    	        consulta.getServicio().getDescripcion(),
	    	        consulta.getServicio().getPrecio(),
	    	        consulta.getServicio().getDuracion()
	    	    ))
	    	    .collect(Collectors.toList());

		}


	@Override
	public List<Consulta_medica> getConsultasPorMedicos(UUID idMedico) {
		return consulRepo.findByMedicoIdPersona(idMedico);
		
	}


	@Override
	public GraficoGeneroDTO getGraficoGenero() {
		List<Consulta_medica> consultas = consulRepo.findAll();
		
		Integer hombre = 0;
		Integer mujer = 0;
		Integer intersexual=0;
		
		for(Consulta_medica consulta: consultas) {
			if(consulta.getPaciente() != null && consulta.getPaciente().getGenero() != null) {
				String genero = consulta.getPaciente().getGenero().toLowerCase();
				if(genero.equals("hombre")) {
					hombre++;
				} else if (genero.equals("mujer")) {
					mujer++;
				} else if (genero.equals("intersexual")) {
					intersexual++;
			}
		}
	}
		
		GraficoGeneroDTO dto = new GraficoGeneroDTO();
		dto.setSexo("Sexo");
		dto.setHombre(hombre);
		dto.setMujer(mujer);
		dto.setIntersexual(intersexual);
		return dto;
		
	}


	@Override
	public List<ServicioConteoDTO> getCountServicec() {
		List<Consulta_medica> consultas = consulRepo.findAll();
		Map<String, Long> conteoServicios = consultas.stream()
	            .filter(consulta -> consulta.getServicio() != null)
	            .collect(Collectors.groupingBy(consulta -> consulta.getServicio().getNombre(), Collectors.counting()));

	    List<ServicioConteoDTO> resultado = new ArrayList<>();
	    int contador = 1; // Para generar IDs únicos

	    for (Map.Entry<String, Long> entry : conteoServicios.entrySet()) {
	        String nombreServicio = entry.getKey();
	        Long conteo = entry.getValue();
	        resultado.add(new ServicioConteoDTO(nombreServicio, nombreServicio, conteo));
	        contador++;
	    }

	    return resultado;
	}


	@Override
	public List<MesConteoDTO> getMesConteo() {
		List<Consulta_medica> consultas = consulRepo.findAll();
		Map<String, Long> conteoServiciosPorMes = consultas.stream()
	            .filter(consulta -> consulta.getServicio() != null && consulta.getFechaTurno() != null)
	            .collect(Collectors.groupingBy(consulta -> obtenerNombreMes(consulta.getFechaTurno().getMonth()), Collectors.counting()));

	    return conteoServiciosPorMes.entrySet().stream()
	            .map(entry -> new MesConteoDTO(entry.getKey(), entry.getValue()))
	            .collect(Collectors.toList());}
	@Override
	public String obtenerNombreMes(Month mes) {
	        switch (mes) {
	            case JANUARY:
	                return "Enero";
	            case FEBRUARY:
	                return "Febrero";
	            case MARCH:
	                return "Marzo";
	            case APRIL:
	                return "Abril";
	            case MAY:
	                return "Mayo";
	            case JUNE:
	                return "Junio";
	            case JULY:
	                return "Julio";
	            case AUGUST:
	                return "Agosto";
	            case SEPTEMBER:
	                return "Septiembre";
	            case OCTOBER:
	                return "Octubre";
	            case NOVEMBER:
	                return "Noviembre";
	            case DECEMBER:
	                return "Diciembre";
	            default:
	                return "Mes inválido";
	        }
	}
}

	
	
			
		
		



