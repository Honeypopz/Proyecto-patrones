/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.caso1.repository;

import com.caso1.domain.Categoria;
import com.caso1.domain.Cliente;
import com.caso1.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jason
 */
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{
    //
}
