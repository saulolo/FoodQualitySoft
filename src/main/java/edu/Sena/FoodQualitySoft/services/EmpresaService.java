package edu.Sena.FoodQualitySoft.services;

import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.repositories.EmpresaRepository;
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
public class EmpresaService {

    private final EmpresaRepository empresaRepository;


    /* --VER TODAS LAS EMPRESAS-- */
    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }


    /* --VER EMPRESAS POR ID-- */
    public Empresa getEmpresaById(Long id) {
        // Buscamos la empresa en el 'empresaRepository' utilizando el método 'findById(id)'
        // y obtenemos el resultado utilizando el método '.get()', que asumimos que siempre encontrará una empresa válida.
        Empresa emp = empresaRepository.findById(id).get();

        // Retornamos la empresa encontrada
        return emp;
    }



    /* --GUARDAR O ACTUALIZAR EMPRESAS-- */
    public boolean saveOrUpdateEmpresa(Empresa empresa) {
        // Guardamos la empresa utilizando el método 'save' del 'empresaRepository'
        Empresa emp = empresaRepository.save(empresa);

        // Verificamos si la operación de guardado fue exitosa
        if (emp != null) {
            // Si se guarda correctamente, retornamos 'true'
            return true;
        }

        // Si no se guarda correctamente, retornamos 'false'
        return false;
    }



    /* --BORRAR EMPRESAS-- */
    public boolean deleteEmpresaById(Long id) {
        // Borramos la empresa del 'empresaRepository' utilizando el método 'deleteById(id)'
        empresaRepository.deleteById(id);

        // Verificamos si la empresa aún existe después de la eliminación
        if (empresaRepository.findById(id) != null) {
            // Si la empresa no  existe, retornamos 'true' para indicar que la eliminación fue exitosa
            return true;
        }

        // Si la empresa existe, retornamos 'false' para indicar que la eliminación no fue exitosa
        return false;
    }


}
