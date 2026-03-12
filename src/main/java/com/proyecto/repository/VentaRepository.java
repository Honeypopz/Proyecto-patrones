/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.repository;

import com.proyecto.domain.Categoria;
import com.proyecto.domain.Cliente;
import com.proyecto.domain.Usuario;
import com.proyecto.domain.Venta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jason
 */
public interface VentaRepository extends JpaRepository<Venta,Integer>{
    //para buscar ventas por cliente
     public List<Venta> findByClienteID(Integer idCliente);
     
     
}
