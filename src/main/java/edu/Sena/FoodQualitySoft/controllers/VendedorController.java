package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.entities.Vendedor;
import edu.Sena.FoodQualitySoft.services.MovimientoService;
import edu.Sena.FoodQualitySoft.services.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/vendedores")
@RestController
public class VendedorController {


    private final VendedorService vendedorService;


    /* --VER TODOS LOS VENDEDORES-- */
    @GetMapping
    public List<Vendedor> verVendedores() {
        return vendedorService.getAllVendedores();
    }


    /* --VER LOS VENDEDORES POR ID-- */
    @GetMapping("/{id}")
    public Vendedor verVendedorById (@PathVariable Long id) {
        Vendedor vendedor = vendedorService.getAllVendedorById(id);
        return vendedor;
    }


    /* --CREAR LOS VENDEDORES-- */
    @PostMapping
    public Vendedor guardarVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.SaveOrUpdateVendedores(vendedor);
    }



    /* --ELIMINAR VENDEDORES-- */
    @DeleteMapping("/{id}")
    public String eliminarVendedor(@PathVariable Long id) {
        boolean respuesta = vendedorService.deleteVendedores(id);
        if (respuesta) {
            return "Se elimino el vendedor con id " + id;
        }
        else {
            return "No se pudo eliminar el vendedor con el id " + id;
        }
    }



}






