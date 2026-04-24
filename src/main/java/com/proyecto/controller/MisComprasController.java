/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Venta;
import com.proyecto.service.VentaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jason
 */
@Controller
@RequestMapping("/MisCompras")
public class MisComprasController {

    private final VentaService ventaService;

    public MisComprasController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    
    @GetMapping("/listado")
    public String listado(Model model, @RequestParam Integer idCliente) {

        var ventas = ventaService.getVenta(idCliente);

        BigDecimal totalGastado = ventas.stream()
                .map(Venta::getTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LocalDate ultimaCompra = ventas.stream()
                .map(Venta::getFecha)
                .filter(Objects::nonNull)
                .max(LocalDate::compareTo)
                .orElse(null);

        model.addAttribute("ventas", ventas);
        model.addAttribute("ventaTotal", ventas.size());
        model.addAttribute("totalGastado", totalGastado);
        model.addAttribute("ultimaCompra", ultimaCompra);

        return "miscompras/listado";
    }
    
}
