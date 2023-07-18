package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoDinero, Long> {

    List<MovimientoDinero> findByVendedorVendedorId(Long vendedorId);


    //Encontrar movimientos filtrados por vendedor y empresa
    List<MovimientoDinero> findByVendedorEmpresaEmpresaId(Long empresaId);


}
