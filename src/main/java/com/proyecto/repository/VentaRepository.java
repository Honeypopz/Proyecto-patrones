package com.proyecto.repository;

import com.proyecto.domain.Venta;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    List<Venta> findByClienteIdCliente(Integer idCliente);

    long countByVendedorIdUsuario(Integer idVendedor);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.vendedor.idUsuario = :idVendedor")
    BigDecimal sumTotalByVendedor(@Param("idVendedor") Integer idVendedor);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.estado = 'completada'")
    BigDecimal sumTotalGlobal();
    
    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.estado = 'completada' AND v.fecha = CURRENT_DATE")
    BigDecimal sumVentasHoy();

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.estado = 'completada' AND MONTH(v.fecha) = MONTH(CURRENT_DATE) AND YEAR(v.fecha) = YEAR(CURRENT_DATE)")
    BigDecimal sumVentasMes();
    
    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.cliente.idCliente = :idCliente AND v.estado = 'completada'")
    BigDecimal sumTotalByCliente(Integer idCliente);
}
