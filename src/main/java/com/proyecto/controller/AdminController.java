package com.proyecto.controller;

import com.proyecto.service.*;
import com.proyecto.domain.VentaForm;
import com.proyecto.domain.Producto;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;

@Controller
public class AdminController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final MessageSource messageSource;

    public AdminController(VentaService ventaService, ClienteService clienteService, ProductoService productoService, CategoriaService categoriaService, MessageSource messageSource) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.messageSource = messageSource;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        long totalClientes = clienteService.getClientes().size();
        long totalProductos = productoService.getProductos().size();
        BigDecimal totalVendido = ventaService.totalVendidoGlobal();

        model.addAttribute("totalClientes", totalClientes);
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("totalVendido", totalVendido);

        return "admin/dashboard";
    }

    @GetMapping("/admin/productos")
    public String productos(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "admin/productos";
    }

    @GetMapping("/admin/clientes")
    public String clientes(Model model) {

        var clientes = clienteService.getClientes();

        model.addAttribute("clientes", clientes);

        return "admin/clientes";
    }

    @GetMapping("/admin/ventas")
    public String ventas(Model model) {

        var ventas = ventaService.getVentas();

        model.addAttribute("ventas", ventas);

        return "admin/ventas";
    }

    @GetMapping("/admin/reportes")
    public String reportes(Model model) {

        BigDecimal ventasHoy = ventaService.ventasHoy();
        BigDecimal ventasMes = ventaService.ventasMes();

        model.addAttribute("ventasHoy", ventasHoy);
        model.addAttribute("ventasMes", ventasMes);

        return "admin/reportes";
    }

    @GetMapping("/admin/producto/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "admin/productoForm";
    }

    @PostMapping("/admin/producto/guardar")
    public String guardar(@Valid Producto producto,
            @RequestParam MultipartFile imagenFile,
            RedirectAttributes redirectAttributes) {
        productoService.save(producto, imagenFile);
        redirectAttributes.addFlashAttribute("todoOk", "Producto guardado correctamente");
        return "redirect:/admin/productos";
    }

    @PostMapping("/admin/producto/eliminar")
    public String eliminar(@RequestParam Integer idProducto,
            RedirectAttributes redirectAttributes) {
        productoService.delete(idProducto);
        redirectAttributes.addFlashAttribute("todoOk", "Producto eliminado correctamente");
        return "redirect:/admin/productos";
    }

    @GetMapping("/admin/producto/modificar/{idProducto}")
    public String modificar(@PathVariable Integer idProducto, Model model) {
        Producto producto = productoService.getProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getCategorias());

        return "admin/productoForm";
    }

}
