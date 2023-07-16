package edu.Sena.FoodQualitySoft.controllers;

import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmpresaController {

    private final EmpresaService empresaService;


    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS-- */
    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresas();
    }


    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS POR ID-- */
    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaById(@PathVariable Long id) {
        return empresaService.getEmpresaById(id);
    }

    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS (MÉTODO 2)-- */
    //Metodo opcional # 2 para ver empresas por Id usando el ciclo for each
/*    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaByIdForEach(@PathVariable Long id) {
        List<Empresa> empresaList = empresaService.getAllEmpresas();
        Empresa empresaEncontrada = null;

        for (Empresa empresa : empresaList) {
            if (empresa.getEmpresaId().equals(id)) {
                empresaEncontrada = empresa;
                break;
            }
        }
        return empresaEncontrada;
    }*/

    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS (MÉTODO 3)-- */
    //Metodo opcional # 3 para ver empresas por Id usando streams con funciones lamda
    /*
    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaByIdUsandoStream(@PathVariable Long id) {
        List<Empresa> empresaList = empresaService.getAllEmpresas();
        return empresaList.stream()
                .filter(empresa -> empresa.getEmpresaId().equals(id))
                .findFirst()
                .orElse(null);
    }
*/

    /* --CONTROLADOR PARA VER LOS PRODUCTOS POR EMPRESA-- */




   /* --CONTROLADOR PARA CREAR UNA EMPRESA-- */
    @PostMapping("/enterprises")
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.saveOrUpdateEmpresa(empresa);
    }
    //Puedo utlizar un post tambien para actualizar y cuando le ponga el id de la empresa en el postman
    //él reconoce que se va a actulizar esa empresa y no ha crearla, pero si quiere decirle especificamente
    // que actualizar, lo hago con un Patch, y le seteo los atributos que quiero actualizar


    /* --CONTROLADOR PARA CREAR UNA EMPRESA (MÉTODO 2)-- */
    //Metodo opcional # 2 para crear empresas usando el método Optional
/*    @PostMapping("/enterprises")
    public Optional<Empresa> crearEmpresaUsandoOptional(@RequestBody Empresa empresa) {
        return Optional.ofNullable(empresaService.saveOrUpdateEmpresa(empresa));
    }*/


    /* --CONTROLADOR PARA CREAR UNA EMPRESA (MÉTODO 3)-- */
    //Metodo opcional # 3 para crear empresas usando el método add(solo para crear en memoria)
/*    @PostMapping("/enterprises")
    public Empresa crearEmpresaUsandoAdd(@RequestBody Empresa empresa) {
        List<Empresa> listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresa);
        return empresa;
    }*/


    /* --CONTROLADOR PARA ACTUALIZAR UNA EMPRESA-- */
    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa (@PathVariable Long id, @RequestBody Empresa empresa){
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNit(empresa.getNit()); //Si le seteo el Id, me creara una nueva empresa, por eso no lo traigo
        emp.setNombreEmpresa(empresa.getNombreEmpresa());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setEmail(empresa.getEmail());
        emp.setCategoriaAlimentos(empresa.getCategoriaAlimentos());
        return empresaService.saveOrUpdateEmpresa(emp);
    }


    /* --CONTROLADOR PARA ELIMINAR EMPRESA POR ID-- */
    //En caso de que me devuelba un string
    @DeleteMapping("/enterprises/{id}")
    public String eliminarEmpresa(@PathVariable Long id) {
        boolean respuesta = empresaService.deleteEmpresaById(id);
        if (respuesta) {
            return "Se elimino la empresa con id " + id;
        }
        else {
            return "No se pudo eliminar la empresa con el id " + id;
        }
    }


    /* --CONTROLADOR PARA ELIMINAR EMPRESA POR ID (MÉTODO 2)-- */
    //Metodo eliminar en caso de que me retorne un Boolean
/*    @DeleteMapping("/enterprises/{id}")
    public boolean eliminarEmpresa(@PathVariable Long id) {
        Empresa emp = empresaService.getEmpresaById(id);
        return  empresaService.deleteEmpresaById(id);
    }*/


}






