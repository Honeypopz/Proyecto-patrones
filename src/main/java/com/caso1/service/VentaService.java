/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caso1.service;

import com.caso1.domain.Producto;
import com.caso1.domain.Venta;
import com.caso1.repository.VentaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jason
 */
@Service
public class VentaService {
    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }
   
    
    @Transactional(readOnly = true)
    public List<Venta> getVentas(){
        return ventaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Venta> getVenta(Integer IdVenta){
        return ventaRepository.findById(IdVenta);
    }
}
