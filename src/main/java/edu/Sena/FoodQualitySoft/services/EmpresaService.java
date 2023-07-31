package edu.Sena.FoodQualitySoft.services;

import edu.Sena.FoodQualitySoft.dto.EmpresaDTO;
import edu.Sena.FoodQualitySoft.dto.ResponseDTO;
import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.repositories.EmpresaRepository;
import edu.Sena.FoodQualitySoft.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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


    /* --VER TODAS LAS EMPRESAS (Utilizando ResponseDTO)-- */
    public ResponseDTO getAllEmpresasDTO() {
        List<Empresa> empresaList = empresaRepository.findAll();
        List<EmpresaDTO> empresaDTOList = new ArrayList<>();

        for (Empresa empresa : empresaList) {
            EmpresaDTO empresaDTO = new EmpresaDTO(
                    empresa.getEmpresaId(),
                    empresa.getNit(),
                    empresa.getNombreEmpresa()
            );
            empresaDTOList.add(empresaDTO);
        }

        return new ResponseDTO(HttpStatus.OK, Constants.EMPTY_MESSAGE, empresaDTOList);
    }

    /* --VER TODAS LAS EMPRESAS (Utilizando Stream y Lamdas)-- */
    //Se usa este método para proporcionar una manera mas elegante de mostrar el código ademas de su legibidad y efeiciencia
    public List<Empresa> getAllEmpresasStreamLamda () {
        List<Empresa> empresaList = empresaRepository.findAll().
                stream().map(empresaEntity -> {
                    Empresa empresa = new Empresa();
                    empresa.setEmpresaId(empresaEntity.getEmpresaId());
                    empresa.setNit(empresaEntity.getNit());
                    empresa.setNombreEmpresa(empresaEntity.getNombreEmpresa());
                    empresa.setDireccion(empresaEntity.getDireccion());
                    empresa.setTelefono(empresaEntity.getTelefono());
                    empresa.setEmail(empresaEntity.getEmail());
                    empresa.setCategoriaAlimentos(empresaEntity.getCategoriaAlimentos());
                    return empresa;
                }).collect(Collectors.toList());

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




    /* --VER EMPRESAS POR ID (utilizando Optional)-- */
    public Optional<Empresa> getEmpresaByIdOpt(Long id) {
        return empresaRepository.findById(id);
    }
    //el optional es un recurso que se pone en lo métodos para que si no encuentra lo que debe de retornar
    //el codigo no se reviente, se utiliza por si regresa algo o no regresa nada.
    //En este caso me controla que si no encuestra el id, no se reviente y me muestre un null


    /* --GUARDAR O ACTUALIZAR EMPRESAS-- */
    public Empresa saveOrUpdateEmpresa(Empresa empresa) {
        // Guardamos la empresa utilizando el método 'save' del 'empresaRepository'
        Empresa emp = empresaRepository.save(empresa);
        return emp;
    }

    /* --GUARDAR O ACTUALIZAR EMPRESAS (Método 2 utilizando mapeamiento manual-- */
/*    public void saveEmpresaUtilizandoEntity(Empresa empresa) {
        Empresa emp = new Empresa();
        emp.setEmpresaId(empresa.getEmpresaId());
        emp.setNit(empresa.getNit());
        emp.setNombreEmpresa(empresa.getNombreEmpresa());
        emp.setDireccion(empresa.getDireccion());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setCategoriaAlimentos(empresa.getCategoriaAlimentos());

        empresaRepository.save(empresa);
    }*/


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


    /* --BORRAR EMPRESAS (utilizando isPresent)-- */
/*    public boolean deleteEmpresaById(Long id) {
        empresaRepository.deleteById(id);

        if (empresaRepository.findById(id).isPresent()) { //Utilizando el metodo .isPresent
            return false;
        }
        return true;
    }*/


}
