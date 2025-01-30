package com.hackacode1.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackacode1.model.Factura;
import com.hackacode1.service.IFacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	IFacturaService factuServ;
	
	@GetMapping("/traer")
	public List<Factura> getfacturas(){
		List<Factura> factu = factuServ.getFacturas();
		return factu;
	}
	
	@GetMapping("/traer/{id}")
		public Factura getfactura(@PathVariable UUID id) {
			return factuServ.getFactura(id);
		}
	
	@DeleteMapping("/borrar/{id}")
	public String borrarFactura(@PathVariable UUID id) {
		factuServ.deleteFactura(id);
		return "Factura borrada con éxito";
	}
	
	@PutMapping("/editar/{id}")
	public Factura editarFactura(@PathVariable UUID id,
								@RequestParam(required=false, name="fecha_emision") LocalDate newFecha_emision,
								@RequestParam(required=false, name="fecha_vencimiento") LocalDate newfecha_vencimiento,
								@RequestParam(required=false, name="monto_total") Double newmonto_total,
								@RequestParam(required=false, name="moneda") String newMoneda,
								@RequestParam(required=false, name="forma_pago") String newforma_pago,
								@RequestParam(required=false, name="estado") String newEstado,
								@RequestParam(required=false, name="descuento") Double newDescuento,
								@RequestParam(required=false, name="iva") Double newIva) {
		factuServ.editFactura(id, newFecha_emision, newfecha_vencimiento, newmonto_total, newMoneda, newforma_pago, newEstado, newDescuento, newIva);
		Factura factura = factuServ.getFactura(id);
		return factura;
		
	}
	
	
	
	}
	

