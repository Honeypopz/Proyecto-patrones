/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.repository;

import com.proyecto.domain.Cliente;
import com.proyecto.domain.Producto;
import com.proyecto.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jason
 */
public interface ProductoRepository extends JpaRepository<Producto,Integer>{
    //por categoria
    public List<Producto> findByCategoriaId(Integer idCategoria);
    
    //todos
    public List<Producto> findAll();
}
