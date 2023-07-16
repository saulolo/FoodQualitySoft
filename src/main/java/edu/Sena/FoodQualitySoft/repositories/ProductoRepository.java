package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    //Asi hago un query cuando quiero encontrar la empresa a la que me pertenece el producto
    //esto es lo mismo el query de sql=> @Query(value="SELECT * FROM productos WHERE empresa_id = ?1;",nativeQuery=true)
    List<Producto> findByEmpresaEmpresaId(Long empresaId);
}
