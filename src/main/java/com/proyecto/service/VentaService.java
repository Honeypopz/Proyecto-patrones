package com.proyecto.service;

import com.proyecto.domain.*;
import com.proyecto.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository,
            DetalleVentaRepository detalleVentaRepository,
            ClienteRepository clienteRepository,
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Venta> getVenta(Integer idCliente) {
        return ventaRepository.findByClienteIdCliente(idCliente);
    }

    public long contarVentasPorVendedor(Integer idVendedor) {
        return ventaRepository.countByVendedorIdUsuario(idVendedor);
    }

    public BigDecimal totalVendidoPorVendedor(Integer idVendedor) {
        BigDecimal total = ventaRepository.sumTotalByVendedor(idVendedor);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Transactional
    public void registrarVenta(VentaForm form) {

        Cliente cliente = clienteRepository.findById(form.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Usuario vendedor = usuarioRepository.findById(form.getIdVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        Producto producto = productoRepository.findById(form.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < form.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        BigDecimal subtotal = producto.getPrecio()
                .multiply(BigDecimal.valueOf(form.getCantidad()));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setVendedor(vendedor);
        venta.setFecha(LocalDate.now());
        venta.setTotal(subtotal);
        venta.setEstado(Venta.Estado.completada);
        venta.setNumeroVenta(generarNumeroVenta());

        venta = ventaRepository.save(venta);

        DetalleVenta detalle = new DetalleVenta();
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(form.getCantidad());
        detalleVentaRepository.save(detalle);

        producto.setStock(producto.getStock() - form.getCantidad());
        productoRepository.save(producto);
    }

    private String generarNumeroVenta() {
        long siguiente = ventaRepository.count() + 1;
        return "V-" + String.format("%03d", siguiente);
    }

    @Transactional(readOnly = true)
    public List<Venta> getVentasPorVendedor(Integer idVendedor) {
        return ventaRepository.findByVendedorIdUsuario(idVendedor);
    }
}
