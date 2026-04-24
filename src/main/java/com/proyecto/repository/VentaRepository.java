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
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
import java.math.BigDecimal;

/**
 *
 * @author Jason
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    List<Venta> findByClienteIdCliente(Integer idCliente);
    
    long countByVendedorIdUsuario(Integer idVendedor);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.vendedor.idUsuario = :idVendedor")
    BigDecimal sumTotalByVendedor(@Param("idVendedor") Integer idVendedor);
    
    List<Venta> findByVendedorIdUsuario(Integer idVendedor);

}
