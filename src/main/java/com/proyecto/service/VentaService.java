/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Producto;
import com.proyecto.domain.Venta;
import com.proyecto.repository.VentaRepository;
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
