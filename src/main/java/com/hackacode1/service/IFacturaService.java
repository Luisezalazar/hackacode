package com.hackacode1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.hackacode1.model.Factura;

public interface IFacturaService {

    public List<Factura> getFacturas();

    public Factura getFactura(UUID id);

    public void saveFactura(Factura factura);

    public void deleteFactura(UUID id);

    public void editFactura(Factura factura);

    public void editFactura(UUID id, LocalDate newFechaEmision, LocalDate newFechaVencimiento, Double newMontoTotal, String newMoneda, String newFormaPago, String newEstado, Double newDescuento, Double newIva);

    
}
