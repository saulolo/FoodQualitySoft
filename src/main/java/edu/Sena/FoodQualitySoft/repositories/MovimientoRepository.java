package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoDinero, Long> {

    List<MovimientoDinero> findByVendedorVendedorId(Long vendedorId);

    //List<MovimientoDinero> findByEmpresaEmpresaIdAndVendedorVendedorId(Long empresaId, Long vendedorId);


    //quede clase 15. min 52:52




}
