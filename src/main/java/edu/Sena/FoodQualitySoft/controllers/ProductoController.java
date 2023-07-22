package edu.Sena.FoodQualitySoft.controllers;

import edu.Sena.FoodQualitySoft.entities.Producto;
import edu.Sena.FoodQualitySoft.exceptions.ResourceNotFoundException;
import edu.Sena.FoodQualitySoft.services.ProductoService;
import edu.Sena.FoodQualitySoft.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/productos")
@RestController
public class ProductoController {

    private final ProductoService productoService;


    @GetMapping
    public ResponseEntity<List<Producto>> verProductos(){
        List<Producto> productoList = productoService.getAllProductos();
        return ResponseEntity.ok(productoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> verProductosById(@PathVariable Long id) {
        List<Producto> productoList = productoService.getAllProductos();

        return productoList.stream()
                .filter(pro -> pro.getProductoId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException(Constants.PRODUCT + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));
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
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {

        List<Producto> productoList = productoService.getAllProductos();

        Producto productoEncontrado = productoList.stream()
                .filter(pro -> pro.getProductoId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PRODUCT + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));

        productoService.deleteProductoById(id);

        return ResponseEntity.ok(productoEncontrado);

    }




}






