package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Factura;
import com.hackacode1.repository.IFacturaRepository;

@Service
public class FacturaService implements IFacturaService{

    @Autowired
    IFacturaRepository facturaRepo;
    public List<Factura> getFacturas(){
        return facturaRepo.findAll();
    }

    public Factura getFactura(UUID id){
        return facturaRepo.findById(id).orElse(null);
    }

    public void saveFactura(Factura factura){
        facturaRepo.save(factura);
    }

    public void deleteFactura(UUID id){
        facturaRepo.deleteById(id);
    }

    public void editFactura(Factura factura)
    {
       this.saveFactura(factura);
    }

    public void editFactura(UUID id, LocalDate newFechaEmision, LocalDate newFechaVencimiento, Double newMontoTotal, String newMoneda, String newFormaPago, String newEstado, Double newDescuento, Double newIva){
        Factura factura = this.getFactura(id);
        factura.setFecha_emision(newFechaEmision);
        factura.setFecha_vencimiento(newFechaVencimiento);
        factura.setMonto_total(newMontoTotal);
        factura.setMoneda(newMoneda);
        factura.setForma_pago(newFormaPago);
        factura.setEstado(newEstado);
        factura.setDescuento(newDescuento);
        factura.setIva(newIva);
        facturaRepo.save(factura);
    }

}
