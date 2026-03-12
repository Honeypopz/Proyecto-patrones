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
import java.util.List;
import lombok.Data;

/**
 *
 * @author Jason
 */
@Data
@Entity
@Table(name="ventas")
public class Venta implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Integer idVenta;
    @Column(name="numero_venta")
    private String numeroVenta;
    
    private LocalDate fecha;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    public enum Estado{
        pendiente,completada
    }
    
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="vendedor_id")
    private Usuario vendedor;
    
    @OneToMany(mappedBy = "venta")
    
    private List<DetalleVenta> detalles;
    
}
