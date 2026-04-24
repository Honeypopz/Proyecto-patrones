package com.proyecto.controller;


import com.proyecto.service.*;
import com.proyecto.domain.VentaForm;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/vendedor")
public class VendedorController {
    
    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    public VendedorController(VentaService ventaService,ClienteService clienteService,ProductoService productoService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }  

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam Integer idVendedor) {

        long totalVentas = ventaService.contarVentasPorVendedor(idVendedor);
        BigDecimal totalVendido = ventaService.totalVendidoPorVendedor(idVendedor);
        BigDecimal comision = totalVendido.multiply(new BigDecimal("0.06"));

        model.addAttribute("totalVentas", totalVentas);
        model.addAttribute("totalVendido", totalVendido);
        model.addAttribute("comision", comision);
        model.addAttribute("idVendedor", idVendedor);

        return "vendedor/dashboard";
    }
    
    @GetMapping("/nuevaVenta")
    public String nuevaVenta(Model model, @RequestParam Integer idVendedor) {
        
        model.addAttribute("clientes", clienteService.getClientes());
        model.addAttribute("productos", productoService.getProductos());
        model.addAttribute("idVendedor", idVendedor);
    return "vendedor/nuevaVenta";
}

    @PostMapping("/registrarVenta")
    public String registrarVenta(VentaForm form) {
    ventaService.registrarVenta(form);
            return "redirect:/vendedor/dashboard?idVendedor=" + form.getIdVendedor();
}

    @GetMapping("/misVentas")
    public String misVentas(Model model, @RequestParam Integer idVendedor) {

        var ventas = ventaService.getVentasPorVendedor(idVendedor);

        model.addAttribute("ventas", ventas);
        model.addAttribute("idVendedor", idVendedor);

        return "vendedor/misVentas";
}

}
