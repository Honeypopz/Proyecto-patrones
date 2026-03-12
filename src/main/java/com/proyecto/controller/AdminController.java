package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/productos")
    public String productos() {
        return "admin/productos";
    }

    @GetMapping("/admin/clientes")
    public String clientes() {
        return "admin/clientes";
    }

    @GetMapping("/admin/ventas")
    public String ventas() {
        return "admin/ventas";
    }

    @GetMapping("/admin/reportes")
    public String reportes() {
        return "admin/reportes";
    }

}
