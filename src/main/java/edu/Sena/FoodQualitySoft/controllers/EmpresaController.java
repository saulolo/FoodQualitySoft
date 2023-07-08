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


    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresas();
    }


    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaById(@PathVariable Long id) {
        return empresaService.getEmpresaById(id);
    }


    @PostMapping("/enterprises")
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.saveOrUpdateEmpresa(empresa);
    }
    //Puedo utlizar un post tambien para actualizar y cuando le ponga el id de la empresa en el postman
    //él reconoce que se va a actulizar esa empresa y no ha crearla, pero si quiere decirle especificamente
    // que actualizar, lo hago con un Patch, y le seteo los atributos que quiero actualizar


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


    //Metodo eliminar en caso de que me rtorne un Boolean
/*    @DeleteMapping("/enterprises/{id}")
    public boolean eliminarEmpresa(@PathVariable Long id) {
        Empresa emp = empresaService.getEmpresaById(id);
        return  empresaService.deleteEmpresaById(id);
    }*/


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


}






