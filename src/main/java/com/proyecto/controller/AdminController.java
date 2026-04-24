package com.proyecto.controller;

import com.proyecto.service.*;
import com.proyecto.domain.VentaForm;
import com.proyecto.domain.Usuario;
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
    private final UsuarioService usuarioService;

    public AdminController(VentaService ventaService, ClienteService clienteService, ProductoService productoService, CategoriaService categoriaService, MessageSource messageSource,UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.messageSource = messageSource;
        this.usuarioService=usuarioService;
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

    
    
    
    //para vendedor tab
    
@GetMapping("/admin/vendedores")
public String vendedores(Model model) {

    System.out.println("ENTRÓ A VENDEDORES");

    var vendedores = usuarioService.getVendedores();

    System.out.println("TOTAL: " + vendedores.size());

    model.addAttribute("vendedores", vendedores);
    return "admin/vendedores";
}

@GetMapping("/admin/vendedores/nuevo")
public String nuevoVendedor(Model model) {
    model.addAttribute("usuario", new Usuario());
    model.addAttribute("roles", Usuario.Rol.values());
    return "admin/vendedorForm";
}
    @PostMapping("/admin/vendedores/guardar")
    public String guardarVendedor(@Valid Usuario usuario, RedirectAttributes redirectAttributes) {
            
        usuarioService.save(usuario);

        redirectAttributes.addFlashAttribute("todoOk", "Vendedor guardado correctamente");
        return "redirect:/admin/vendedores";
    }

    @GetMapping("/admin/vendedores/modificar/{idUsuario}")
    public String modificarVendedor(@PathVariable Integer idUsuario, Model model) {
    Usuario usuario = usuarioService.getUsuario(idUsuario)
            .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

    model.addAttribute("usuario", usuario);
    model.addAttribute("roles", Usuario.Rol.values());

    return "admin/vendedorForm";
}

    @PostMapping("/admin/vendedores/eliminar")
    public String eliminarVendedor(@RequestParam Integer idUsuario, RedirectAttributes redirectAttributes) {
        usuarioService.delete(idUsuario);

        redirectAttributes.addFlashAttribute("todoOk", "Vendedor eliminado correctamente");
        return "redirect:/admin/vendedores";
    }
}
