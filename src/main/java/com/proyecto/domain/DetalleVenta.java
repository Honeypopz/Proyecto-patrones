/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Jason
 */
@Data
@Entity
@Table(name="detalle_ventas")
public class DetalleVenta implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_detalle")
    private Integer idDetalle;
    
   
    private Integer cantidad;
    
    @Column(name="precio_unitario")
    private BigDecimal precioUnitario;
    
    private BigDecimal subtotal;
    
    @ManyToOne
    @JoinColumn(name="venta_id")
    private Venta venta;
    
    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;
    
    
}
