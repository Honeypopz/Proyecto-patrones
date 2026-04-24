/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Jason
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenta {
    private Integer idProducto;
    private String codigo;
    private String nombre;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;
}
