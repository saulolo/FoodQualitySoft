package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoDinero, Long> {

    List<MovimientoDinero> findByVendedorVendedorId(Long vendedorId);


    //Encontrar movimientos filtrados por vendedor y empresa
    List<MovimientoDinero> findByVendedorEmpresaEmpresaId(Long empresaId);


    //Método para recuperar los movimientos de dinero que seam menores a $550.000
    List<MovimientoDinero> findByMontoLessThan(Long monto);


}
