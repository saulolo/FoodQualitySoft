package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
