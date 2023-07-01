package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
