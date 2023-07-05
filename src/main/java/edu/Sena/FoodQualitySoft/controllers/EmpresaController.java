package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping
public class EmpresaController {

    private final EmpresaService empresaService;



    @GetMapping("VerEmpresas")
    public String viewEmpresas(Model model) {
        // Obtener la lista de empresas utilizando el método getAllEmpresas() del servicio empresaService
        List<Empresa> empresaList = empresaService.getAllEmpresas();

        // Agregar la lista de empresas al modelo de la vista con el nombre "empList"
        model.addAttribute("empList", empresaList);

        // Devolver el nombre de la vista "verEmpresas" que se mostrará al cliente
        return "verEmpresas";
    }


    @GetMapping("/agregarEmpresa")  //Es de tipo get porque va a mostar un String (html)
    public String createdEmpresa(Model model) {  //El metodo es string porque devuelve el nombre del template
        // Crea una nueva instancia de la clase Empresa
        Empresa empr = new Empresa();

        // Agrega la instancia de Empresa al modelo
        model.addAttribute("emp", empr);

        // Devuelve el nombre de la vista "agregarEmpresa"
        return "agregarEmpresa";
    }


    @PostMapping("/guardarEmpresa") //El atributo RedirectAttributes me permite redireccionar
    public String saveEmpresa(Empresa empresa, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(empresa) == true) {
            return "redirect:/VerEmpresas";
        }
        return "agregarEmpresa";
        //el redirect se pone si necesito que me lleve a otro servicio, en caso tal solo pongo el tenplate o pag web
    }

    @GetMapping("/editarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Long id){
        Empresa emp=empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        return "editarEmpresa";
    }

    @PostMapping("/actualizarEmpresa")
    public String updateEmpresa(Empresa empresa) { //tambien se podria poner asi: (@ModelAttribute("emp") Empresa empresa)
        if (empresaService.saveOrUpdateEmpresa(empresa)) { //le quito el == true porque lo asume por defecto
            return "redirect:/VerEmpresas";
        }
        return "editarEmpresa";
    }

    @GetMapping("/eliminarEmpresa/{id}")  //Es un GetMapping porque consulta y quita pero no lleva nada
    public String eliminarEmpresa(@PathVariable Long id) {
        try{
            empresaService.deleteEmpresaById(id);
        } catch (Exception e) {
            return "redirect:/VerEmpresas";
        }
        return "redirect:/VerEmpresas";
    }

}






