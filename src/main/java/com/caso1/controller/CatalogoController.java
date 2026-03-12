/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caso1.controller;

import com.caso1.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jason
 */
@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
    private final ProductoService productoService;

    public CatalogoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model){
        var productos=productoService.getProductos();
        model.addAttribute("productos",productos);
        return "catalogo/listado";
    }
    
}
