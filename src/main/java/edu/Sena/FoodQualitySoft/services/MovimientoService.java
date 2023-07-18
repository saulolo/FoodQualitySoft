package edu.Sena.FoodQualitySoft.services;

import edu.Sena.FoodQualitySoft.entities.MovimientoDinero;
import edu.Sena.FoodQualitySoft.repositories.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;


    /* --VER TODOS LOS MOVIMIENTOS-- */
    public List<MovimientoDinero> getAllMovimientos() {
        List<MovimientoDinero> movimientoDineroList = new ArrayList<>();
        movimientoRepository.findAll().forEach(move -> movimientoDineroList.add(move));
        return movimientoDineroList;
    }


    /* --VER TODOS LOS MOVIMIENTOS POR ID-- */
    public MovimientoDinero getAllMovimientoById(Long id) {
        MovimientoDinero dinero = movimientoRepository.findById(id).get();
        return dinero;
    }


    /* --VER TODOS LOS MOVIMIENTOS POR VENDEDOR-- */
    public List<MovimientoDinero> getAllMovimientosByVendedor(Long id) {
        return movimientoRepository.findByVendedorVendedorId(id);
    }


    /* --VER TODOS LOS MOVIMIENTOS POR EMPRESA-- */
    public List<MovimientoDinero> getAllMovimientosByVendedorByEmpresa(Long id) {
        return movimientoRepository.findByVendedorEmpresaEmpresaId(id);
    }


    /* --GUARDAR LOS MOVIMIENTOS -- */
    public MovimientoDinero SaveOrUpdateMovimientos(MovimientoDinero movimientoDinero) {
        MovimientoDinero dinero = movimientoRepository.save(movimientoDinero);
        return dinero;
    }


    /* --ELIMINAR LOS MOVIMIENTOS -- */
    public boolean deleteMovimientos(Long id) {
        movimientoRepository.deleteById(id);
        if (movimientoRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }



}
