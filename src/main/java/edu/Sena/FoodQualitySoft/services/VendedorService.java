package edu.Sena.FoodQualitySoft.services;

import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.entities.Producto;
import edu.Sena.FoodQualitySoft.entities.Vendedor;
import edu.Sena.FoodQualitySoft.repositories.ProductoRepository;
import edu.Sena.FoodQualitySoft.repositories.VendedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;


    /* --VER TODOS LOS VENDEDORES-- */
    public List<Vendedor> getAllVendedores() {
        List<Vendedor> vendedorList = new ArrayList<>();
        vendedorRepository.findAll().forEach(vend -> vendedorList.add(vend));
        return vendedorList;
    }

    /* --VER TODOS LOS VENDEDORES POR ID-- */
    public Vendedor getAllVendedorById(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id).get();
        return vendedor;
    }

     /*-- VER TODOS LOS VENDEDORES POR SU NOMBRE-- */
/*    public List<Vendedor> getAllVendoresByNombre() {
        List<Vendedor> vendedorList = vendedorRepository.findByNombreLike("Felipe");
        return  vendedorList;
    }*/



    /* --VER TODOS LOS VENDEDORES POR SU NOMBRE (Metodo 2 Utlizando Stream(anque no es necesario)-- */
    public List<Vendedor> getAllVendedoresByNombre(String nombre) {
        List<Vendedor> vendedorList = vendedorRepository.findByNombreLike(nombre);

        // Utilizar Stream y map para realizar alguna transformación si es necesario
        List<Vendedor> vendedoresTransformados = vendedorList.stream()
                .map(vendedor -> {
                    Vendedor vendedorTransformado = new Vendedor();
                    // Realizar cualquier transformación necesaria en el objeto vendedor
                    vendedorTransformado.setNombre(vendedor.getNombre());
                    // Puedes copiar los campos que necesites o modificarlos según tus necesidades
                    return vendedorTransformado;
                })
                .collect(Collectors.toList());

        return vendedoresTransformados;
    }



    /* --GUARDAR LOS VENDEDROES -- */
    public Vendedor SaveOrUpdateVendedores(Vendedor vendedor) {
        Vendedor vend = vendedorRepository.save(vendedor);
        return vend;
    }



    /* --ELIMINAR LOS MOVIMIENTOS -- */
    public boolean deleteVendedorById(Long id) {
        vendedorRepository.deleteById(id);
        if (vendedorRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }






}
