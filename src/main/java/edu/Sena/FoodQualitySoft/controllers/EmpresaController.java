package edu.Sena.FoodQualitySoft.controllers;


import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;



    @GetMapping("/verEmpresas")
    public String viewEmpresas(Model model) {
        // Obtener la lista de empresas utilizando el método getAllEmpresas() del servicio empresaService
        List<Empresa> empresaList = empresaService.getAllEmpresas();

        // Agregar la lista de empresas al modelo de la vista con el nombre "empList"
        model.addAttribute("empList", empresaList);

        // Devolver el nombre de la vista "verEmpresas" que se mostrará al cliente
        return "verEmpresas";
    }





    //Terminar los otros contoladores de guardar, editar y eliminar




}
