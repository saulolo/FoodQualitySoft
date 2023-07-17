package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.entities.Producto;
import edu.Sena.FoodQualitySoft.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/movimientos")
@RestController
public class MovimientoController {


    private final MovimientoService movimientoService;


    /* --VER TODOS LOS MOVIMIENTOS-- */
    @GetMapping
    public List<MovimientoDinero> verMovimientos() {
        return movimientoService.getAllMovimientos();
    }


    /* --VER LOS MOVIMIENTOS POR ID-- */
    @GetMapping("/{id}")
    public MovimientoDinero verMovimientoById (@PathVariable Long id) {
        MovimientoDinero dinero = movimientoService.getAllMovimientoById(id);
        return dinero;
    }



    /* --VER LOS MOVIMIENTOS POR VENDEDOR-- */
    @GetMapping("/vendedor/{vendedorId}")
    public List<MovimientoDinero> verMovimientosByVendedor(@PathVariable Long vendedorId) {
        return movimientoService.getAllMovimientosByVendedor(vendedorId);
    }



    /* --CREAR MOVIMIENTOS-- */
    @PostMapping
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimientoDinero) {
        return movimientoService.SaveOrUpdateMovimientos(movimientoDinero);
    }



















}






