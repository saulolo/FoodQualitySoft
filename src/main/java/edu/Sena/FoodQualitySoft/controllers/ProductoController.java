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


    //Método para ver productos por empresa
    @GetMapping("/empresas/{empresaId}")
    public List<Producto> verProductosByEmpresa(@PathVariable Long empresaId) {
        return productoService.getProductosByEmpresa(empresaId);
    }



    @PostMapping
    public Optional<Producto> crearProducto(@RequestBody Producto producto) {
        return Optional.ofNullable(productoService.saveOrUpdateProducto(producto));
    }



    @PatchMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto product = productoService.getProductoById(id); //hay veces pide  .get()
        product.setReferencia(producto.getReferencia());
        product.setNombreProducto(producto.getNombreProducto());
        product.setGramaje(producto.getGramaje());
        product.setDescripcion(producto.getDescripcion());
        return productoService.saveOrUpdateProducto(product);
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














}






