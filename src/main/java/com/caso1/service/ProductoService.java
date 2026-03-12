/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caso1.service;

import com.caso1.domain.Producto;
import com.caso1.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jason
 */
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Producto> getProducto(Integer IdProducto){
        return productoRepository.findById(IdProducto);
    }
}
