package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
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


    /* --VER LOS MOVIMIENTOS POR VENDEDOR Y POR EMPRESA-- */
    @GetMapping("/vendedores/empresas/{empresaId}")
    public List<MovimientoDinero> verMovimientosByVendedorByEmpresa(@PathVariable Long empresaId) {
        return movimientoService.getAllMovimientosByVendedorByEmpresa(empresaId);
    }


    /* --CREAR MOVIMIENTOS-- */
    @PostMapping
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimientoDinero) {
        return movimientoService.SaveOrUpdateMovimientos(movimientoDinero);
    }


    /* --EDITAR MOVIMIENTOS-- */
    @PatchMapping("/{id}")
    public MovimientoDinero actualizarMovimiento(@PathVariable Long id, @RequestBody MovimientoDinero movimientoDinero) {
        MovimientoDinero mov = movimientoService.getAllMovimientoById(id);
        mov.setMonto(movimientoDinero.getMonto());
        mov.setConcepto(movimientoDinero.getConcepto());
        mov.setVendedor(movimientoDinero.getVendedor());
        return movimientoService.SaveOrUpdateMovimientos(mov);
    }


    /* --ELIMINAR MOVIMIENTOS-- */
    @DeleteMapping("/{id}")
    public String deleteMovimiento(@PathVariable Long id) {
        boolean respuesta = movimientoService.deleteMovimientos(id);
        if (respuesta) {
            return "Se elimino correctamente el movimiento con id " + id;
        }
        return "No se pudo elimino el movimiento con id " + id;
    }


}






