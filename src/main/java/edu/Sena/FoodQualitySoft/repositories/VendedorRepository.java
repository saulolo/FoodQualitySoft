package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

}
