package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.exceptions.ResourceNotFoundException;
import edu.Sena.FoodQualitySoft.services.MovimientoService;
import edu.Sena.FoodQualitySoft.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/movimientos")
@RestController
public class MovimientoController {


    private final MovimientoService movimientoService;


    /* --VER TODOS LOS MOVIMIENTOS-- */
    @GetMapping
    public ResponseEntity<List<MovimientoDinero>> verMovimientos() {
        List<MovimientoDinero> movimientoDineroList = movimientoService.getAllMovimientos();
        return ResponseEntity.ok(movimientoDineroList);
    }


    /* --VER LOS MOVIMIENTOS POR ID-- */
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDinero> verMovimientoById (@PathVariable Long id) {
        List<MovimientoDinero> movimientoDineroList = movimientoService.getAllMovimientos();
        return movimientoDineroList.stream()
                .filter(mov -> mov.getMovimientoId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException(Constants.MOVING_MONEY + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));

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
    public ResponseEntity<MovimientoDinero> deleteMovimiento(@PathVariable Long id) {

        List<MovimientoDinero> movimientoList = movimientoService.getAllMovimientos();

        MovimientoDinero dineroEncontrado = movimientoList.stream()
                .filter(mov -> mov.getMovimientoId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Constants.MOVING_MONEY + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));

        movimientoService.deleteMovimientoById(id);

        return ResponseEntity.ok(dineroEncontrado);
    }



}






