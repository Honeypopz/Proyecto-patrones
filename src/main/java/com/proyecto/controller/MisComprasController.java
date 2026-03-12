/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.service.VentaService;
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
    public String listado(Model model, @RequestParam Integer idCliente){
        var venta=ventaService.getVenta(idCliente);
        model.addAttribute("ventas",venta);
        return "miscompras/listado";
    }
    
}
