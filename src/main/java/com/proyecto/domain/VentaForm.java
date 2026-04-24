/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;
import lombok.Data;
import java.math.BigDecimal;
/**
 *
 * @author Jason
 */
@Data
public class VentaForm {
    private Integer idCliente;
    private Integer idVendedor;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precio;
}
