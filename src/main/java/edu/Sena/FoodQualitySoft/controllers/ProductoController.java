package edu.Sena.FoodQualitySoft.controllers;

import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.entities.Producto;
import edu.Sena.FoodQualitySoft.services.EmpresaService;
import edu.Sena.FoodQualitySoft.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/productos")
@RestController
public class ProductoController {

    private final ProductoService productoService;


    @GetMapping
    public List<Producto> verProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Producto verProductosById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }


    @PostMapping
    public Optional<Producto> crearProducto(@RequestBody Producto producto) {
        return Optional.ofNullable(productoService.saveOrUpdateProducto(producto));
    }


    @DeleteMapping("/{id}")
    public String eliminarProductos(@PathVariable Long id) {
        boolean respuesta = productoService.deleteProductoById(id);
        if (respuesta) {
            return "Se elimino el producto con id " + id;
        }
        else {
            return "No se pudo eliminar el producto con el id " + id;
        }
    }




    //Voy en 1:03:00
/*
    //corregir método
    @PatchMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto product = productoService.getProductoById(id);
        product.setReferencia(producto.getReferencia());
        product.setNombreProducto(producto.getNombreProducto());
        product.setGramaje(producto.getGramaje());
        product.setDescripcion(producto.getDescripcion());
        return productoService.saveOrUpdateProducto(product);
    }
*/









/*    @PostMapping("/enterprises")
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.saveOrUpdateEmpresa(empresa);
    }*/

}






