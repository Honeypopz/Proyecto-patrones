package com.proyecto.controller;

import com.proyecto.service.ClienteService;
import com.proyecto.service.VentaService;
import com.proyecto.domain.Cliente;
import com.proyecto.domain.Venta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final VentaService ventaService;

    public PerfilController(ClienteService clienteService, VentaService ventaService) {
        this.clienteService = clienteService;
        this.ventaService = ventaService;
    }

    @GetMapping("/listado")
    public String listado(Model model, @RequestParam Integer idCliente) {

        Cliente cliente = clienteService.getCliente(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        var ventas = ventaService.getVenta(idCliente);

        model.addAttribute("ventaTotal", ventas.size());
        model.addAttribute("cliente", cliente);

        return "perfil/listado";
    }

    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Integer idCliente,
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String direccion) {

        Cliente cliente = clienteService.getCliente(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(nombre);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);

        clienteService.save(cliente);

        return "redirect:/perfil/listado?idCliente=" + idCliente;
    }

}
