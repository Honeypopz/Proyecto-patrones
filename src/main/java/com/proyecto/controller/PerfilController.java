/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.service.ClienteService;
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
@RequestMapping("/perfil")
public class PerfilController {
    private final ClienteService clienteService;

    public PerfilController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    
    @GetMapping("/listado")
    public String listado(Model model, @RequestParam Integer idCliente){
        var cliente=clienteService.getCliente(idCliente);
        model.addAttribute("cliente",cliente);
        return "perfil/listado";
    }
    
}
