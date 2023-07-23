package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaApiExternaRepository extends JpaRepository<Empresa, Long> {


}
