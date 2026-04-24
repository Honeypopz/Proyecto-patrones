package com.proyecto.controller;

import com.proyecto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductoService productoService;

    public HomeController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/home")
    public String home(Model model){
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "index";
    }
}