package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "vendedor/dashboard";
    }

    @GetMapping("/nuevaVenta")
    public String nuevaVenta() {
        return "vendedor/nuevaVenta";
    }

    @GetMapping("/misVentas")
    public String misVentas() {
        return "vendedor/misVentas";
    }

}
