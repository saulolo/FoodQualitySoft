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


    //WildCard para filtrar por gramaje mayor a 250 y referencia menos a 101
    //Asi seria el query nativo de esta sentencia:
    //@Query(value = "SELECT * FROM Productos WHERE referencia < :referencia AND gramaje > :gramaje", nativeQuery = true)
    //List<Producto> findByReferenciaLessThanAndGramajeGreaterThan(@Param("referencia") int referencia, @Param("gramaje") double gramaje);
    List<Producto> findByReferenciaLessThanAndGramajeGreaterThan(int referencia, double gramaje);



}
