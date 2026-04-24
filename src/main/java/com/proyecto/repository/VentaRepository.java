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

    List<Venta> findByVendedorIdUsuario(Integer idVendedor);
}
