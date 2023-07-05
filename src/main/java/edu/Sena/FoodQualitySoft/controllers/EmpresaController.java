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
    public String viewEmpresas(Model model, @ModelAttribute("mensaje")String mensaje) {
        //Recibo el parametro @ModelAttribute... porque me va aresibir el mensaje implementado en los otros servicios
        // Obtener la lista de empresas utilizando el método getAllEmpresas() del servicio empresaService
        List<Empresa> empresaList = empresaService.getAllEmpresas();

        // Agregar la lista de empresas al modelo de la vista con el nombre "empList" y el de "mensaje"
        model.addAttribute("empList", empresaList);
        model.addAttribute("mensaje", mensaje);

        // Devolver el nombre de la vista "verEmpresas" que se mostrará al cliente
        return "verEmpresas"; //llamamos el html
        //Los que nos retornan un template, son a los que les llega el @ModelAttribute("mensaje")....
    }


    @GetMapping("/agregarEmpresa")  //Es de tipo get porque va a mostar un String (html)
    public String cargarCreatedEmpresa(Model model, @ModelAttribute("mensaje")String mensaje) {  //El metodo es string porque devuelve el nombre del template
        // Crea una nueva instancia de la clase Empresa
        Empresa empr = new Empresa();

        // Agrega la instancia de Empresa al modelo
        model.addAttribute("emp", empr);
        model.addAttribute("mensaje", mensaje);

        // Devuelve el nombre de la vista "agregarEmpresa"
        return "agregarEmpresa";
    }


    @PostMapping("/guardarEmpresa") //El atributo RedirectAttributes me permite redireccionar
    public String saveEmpresa(Empresa empresa, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(empresa) == true) {
            redirectAttributes.addFlashAttribute("mensaje","saveOk");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/agregarEmpresa";
        //el redirect se pone si necesito que me lleve a otro servicio (va a la BD), en caso tal solo pongo el tenplate o pag web
    }

    @GetMapping("/editarEmpresa/{id}")
    public String cargarEditarEmpresa(Model model, @PathVariable Long id, @ModelAttribute("mensaje")String mensaje){
        Empresa emp=empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/actualizarEmpresa")
    public String updateEmpresa(Empresa empresa, RedirectAttributes redirectAttributes) { //tambien se podria
        // poner asi: (@ModelAttribute("emp") Empresa empresa), el otro atributo es para poner el mensaje
        if (empresaService.saveOrUpdateEmpresa(empresa)) { //le quito el == true porque lo asume por defecto
            redirectAttributes.addFlashAttribute("mensaje","updateOk"); //Asi pongo el mensaje
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/editarEmpresa";
    }

    @GetMapping("/eliminarEmpresa/{id}")  //Es un GetMapping porque consulta y quita pero no lleva nada
    public String eliminarEmpresa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if(empresaService.deleteEmpresaById(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOk");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerEmpresas";
    }



}






