package edu.Sena.FoodQualitySoft.services;

import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.entities.Producto;
import edu.Sena.FoodQualitySoft.repositories.EmpresaRepository;
import edu.Sena.FoodQualitySoft.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;


    public List<Producto> getAllProductos() {
        List<Producto> productoList = new ArrayList<>();
        productoRepository.findAll().forEach(producto -> productoList.add(producto));
        return productoList;
    }

    public Producto getProductoById (Long id) {
        Producto producto = productoRepository.findById(id).get();
        return producto;
    }

    //Metodo para buscar Productos por empresas
    public List<Producto> getProductosByEmpresa(Long id) {
        return productoRepository.findByEmpresaEmpresaId(id);
    }



    public Producto saveOrUpdateProducto(Producto producto) {
        //producto.setProductoId(null); // Eliminar la asignación manual del ID
        Producto prod = productoRepository.save(producto);
        return prod;
    }



    public boolean deleteProductoById(Long id) {
        productoRepository.deleteById(id);

        if (productoRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }



}
