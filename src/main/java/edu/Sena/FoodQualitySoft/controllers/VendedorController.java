package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.entities.Vendedor;
import edu.Sena.FoodQualitySoft.exceptions.ResourceNotFoundException;
import edu.Sena.FoodQualitySoft.services.MovimientoService;
import edu.Sena.FoodQualitySoft.services.VendedorService;
import edu.Sena.FoodQualitySoft.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Vendedor> verVendedorById (@PathVariable Long id) {
        List<Vendedor> vendedorList = vendedorService.getAllVendedores();
        return vendedorList.stream()
                .filter(ven -> ven.getVendedorId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.SELLER + Constants.SPACE_SEPARATOR.concat(id.toString()).concat(Constants.SPACE_SEPARATOR + Constants.NOT_FOUND)));
    }


    /* --CREAR LOS VENDEDORES-- */
    @PostMapping
    public Vendedor guardarVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.SaveOrUpdateVendedores(vendedor);
    }




    /* --ELIMINAR VENDEDORES-- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Vendedor> eliminarVendedor(@PathVariable Long id) {

        List<Vendedor> vendedorList = vendedorService.getAllVendedores();

        Vendedor vendedorEncontrado = vendedorList.stream()
                .filter(ved -> ved.getVendedorId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Constants.SELLER + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));

        vendedorService.deleteVendedorById(id);

        return ResponseEntity.ok(vendedorEncontrado);


    }






}






