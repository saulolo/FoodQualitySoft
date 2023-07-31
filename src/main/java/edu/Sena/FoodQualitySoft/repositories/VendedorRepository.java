package edu.Sena.FoodQualitySoft.repositories;

import edu.Sena.FoodQualitySoft.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {


    //Metodo que me busca el nombre del vendedor
    //Like permite realizar búsquedas más flexibles y útiles en casos en los que no necesitas coincidencias
    // exactas, sino coincidencias parciales o aproximadas en los datos almacenados.
    List<Vendedor> findByNombreLike(String nombre);



}
