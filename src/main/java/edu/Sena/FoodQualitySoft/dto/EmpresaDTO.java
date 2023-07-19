package edu.Sena.FoodQualitySoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EmpresaDTO implements Serializable {


    private Long empresaId;
    private String Nit;
    private String nombreEmpresa;



}
